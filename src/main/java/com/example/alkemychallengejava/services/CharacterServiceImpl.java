package com.example.alkemychallengejava.services;

import com.example.alkemychallengejava.entities.Character;
import com.example.alkemychallengejava.exception.ErrorMessage;
import com.example.alkemychallengejava.repository.CharacterRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    CharacterRepository characterRepository;
    @Override
    public Character getCharacter(Long id) {
        Optional<Character> characterOptional = characterRepository.findById(id);

        if(characterOptional.isEmpty()){
            return null;
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
            throw new IllegalArgumentException(ErrorMessage.CHARACTER_HAS_ID.getMessage());
        }

        return characterRepository.save(character);
    }

    @Override
    public Character updateCharacter(@NonNull Character character) {
        if(character.getId() == null){
            throw new IllegalArgumentException(ErrorMessage.CHARACTER_HAS_NO_ID.getMessage());
        }

        if(characterRepository.findById(character.getId()).isEmpty()){
            throw new IllegalArgumentException(ErrorMessage.CHARACTER_NOT_FOUND.getMessage());
        }

        return characterRepository.save(character);
    }

    @Override
    public void deleteCharacter(Long id) {
        if(characterRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException((ErrorMessage.CHARACTER_NOT_FOUND.getMessage()));
        }

        characterRepository.deleteById(id);
    }
}
