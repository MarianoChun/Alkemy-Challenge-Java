package com.example.alkemychallengejava.services;

import com.example.alkemychallengejava.entities.Character;
import com.example.alkemychallengejava.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    @Override
    public Character getCharacter(Long id) {
        return characterRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Character> getAllCharacters() {

        if(characterRepository.findAll().isEmpty()){
            return new ArrayList<>();
        }

        return characterRepository.findAll();
    }

    @Override
    public Character saveCharacter(Character character) {
        if(character.getId() != null){
            throw new IllegalArgumentException("Error: The character to create has an id");
        }

        return characterRepository.save(character);
    }

    @Override
    public Character modifyCharacter(Character character) {
        if(character.getId() == null){
            throw new IllegalArgumentException("Error: The character to modify don't have an id");
        }

        if(characterRepository.findById(character.getId()).isEmpty()){
            throw new IllegalArgumentException("Error: The character to modify doesn't exists");
        }

        return characterRepository.save(character);
    }

    @Override
    public void deleteCharacter(Long id) {
        if(characterRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("Error: The character to delete doesn't exists/was already deleted");
        }

        characterRepository.deleteById(id);
    }
}
