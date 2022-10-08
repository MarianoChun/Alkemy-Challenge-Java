package com.example.alkemychallengejava.services;

import com.example.alkemychallengejava.entities.Character;
import com.example.alkemychallengejava.entities.Movie;
import com.example.alkemychallengejava.exception.ErrorMessage;
import com.example.alkemychallengejava.repository.CharacterRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    MovieService movieService;
    @Override
    public Character getCharacter(Long id) {
        Optional<Character> characterOptional = characterRepository.findById(id);

        if(characterOptional.isEmpty()){
            return new Character();
        }

        return characterOptional.get();
    }

    @Override
    public Iterable<Character> getAllCharacters() {

        if(characterRepository.findAll().isEmpty()){
            return new ArrayList<>();
        }

        return characterRepository.findAll();
    }

    @Override
    public Character saveCharacter(@NonNull Character character) {
        if(character.getId() != null){
            throw new IllegalArgumentException(ErrorMessage.RESOURCE_HAS_ID.getMessage());
        }

        if(findByName(character.getName()) != null){
            throw new IllegalArgumentException(ErrorMessage.RESOURCE_ALREADY_EXISTS.getMessage());
        }

        character.setName(character.getName().toLowerCase());
        character.setMoviesAssociated(character.getMoviesAssociated()
                .stream()
                .map(movie -> {
                    Movie actualMovie = movie;
                    if(movieService.existsMovie(actualMovie.getTitle())){
                        actualMovie = movieService.findByTitle(movie.getTitle());
                    }
                    actualMovie.addCharacterAssociated(character);
                    return actualMovie;
                })
                .collect(Collectors.toSet()));

        return characterRepository.save(character);
    }

    @Override
    public Character updateCharacter(@NonNull Character character) {
        if(character.getId() == null){
            throw new IllegalArgumentException(ErrorMessage.RESOURCE_HAS_NO_ID.getMessage());
        }

        if(characterRepository.findById(character.getId()).isEmpty()){
            throw new IllegalArgumentException(ErrorMessage.RESOURCE_NOT_FOUND.getMessage());
        }

        return characterRepository.save(character);
    }

    @Override
    public void deleteCharacter(Long id) {
        if(characterRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException((ErrorMessage.RESOURCE_NOT_FOUND.getMessage()));
        }

        characterRepository.deleteById(id);
    }

    @Override
    public Character findByName(String name) {
         Optional<Character> characterOptional = characterRepository.findAll().stream()
                .filter(character -> character.getName().equals(name))
                .findFirst();

         return characterOptional.orElse(new Character());
    }

    @Override
    public Iterable<Character> filterByAge(Integer age) {
        Stream<Character> characterOptional = characterRepository.findAll().stream()
                .filter(character -> character.getAge().equals(age));

        return characterOptional.toList();
    }

    @Override
    public Iterable<Character> filterByWeight(Double weight) {
        Stream<Character> characterOptional = characterRepository.findAll().stream()
                .filter(character -> character.getWeight().equals(weight));

        return characterOptional.toList();
    }

    @Override
    public Iterable<Character> filterByMovie(Long idMovie) {
        Movie movie = movieService.findById(idMovie);
        if(movie == null){
            throw new RuntimeException(ErrorMessage.RESOURCE_NOT_FOUND.getMessage());
        }

        return movie.getCharactersAssociated();
    }

}
