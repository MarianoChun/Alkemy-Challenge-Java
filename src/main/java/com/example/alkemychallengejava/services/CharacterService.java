package com.example.alkemychallengejava.services;

import com.example.alkemychallengejava.entities.Character;
import com.example.alkemychallengejava.entities.Movie;

import java.util.List;

public interface CharacterService {

    Character getCharacter(Long id);

    Iterable<Character> getAllCharacters();

    Character saveCharacter(Character character);

    Character updateCharacter(Character character);

    void deleteCharacter(Long id);
    Character findByName(String name);
    Iterable<Character> filterByAge(Integer age);
    Iterable<Character> filterByWeight(Double weight);
    Iterable<Character> filterByMovie(Long idMovie);
}
