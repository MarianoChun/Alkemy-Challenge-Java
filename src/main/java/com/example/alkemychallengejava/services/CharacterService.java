package com.example.alkemychallengejava.services;

import com.example.alkemychallengejava.entities.Character;

public interface CharacterService {

    Character getCharacter(Long id);

    Iterable<Character> getAllCharacters();

    Character saveCharacter(Character character);

    Character updateCharacter(Character character);

    void deleteCharacter(Long id);

}
