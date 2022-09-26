package com.example.alkemychallengejava.controllers;

import com.example.alkemychallengejava.DTO.CharacterDTO;
import com.example.alkemychallengejava.entities.Character;
import com.example.alkemychallengejava.repository.CharacterRepository;
import com.example.alkemychallengejava.services.CharacterService;
import com.example.alkemychallengejava.services.CharacterServiceImpl;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CharacterControllerTest {

    @Autowired
    @Mock
    CharacterService characterService;

    @Autowired
    @Mock
    CharacterRepository characterRepository;

    @Autowired
    @InjectMocks
    CharacterController characterController;

    @Test
    void getCharactersTest() {
        ArrayList<Character> characters = new ArrayList<>();
        characters.add(new Character(null, "image1.jpg", "Mickey", 30, 70.0, "He is funny", new ArrayList<>()));
        characters.add(new Character(null, "image2.jpg", "Pluto", 10, 89.0, "He is big", new ArrayList<>()));

        when(characterService.getAllCharacters()).thenReturn(characters);
        List<CharacterDTO> characterDTOListExpected = getCharacterDTOStream().toList();

        assertEquals(characterDTOListExpected, characterController.getCharacters().getBody());
    }

    private Stream<CharacterDTO> getCharacterDTOStream() {
        return StreamSupport.stream(characterService.getAllCharacters().spliterator(), false)
                .map(character -> new CharacterDTO(character.getId(), character.getImage(), character.getName()));
    }

    @Test
    void getCharactersWithEmptyRepositoryTest() {
        when(characterService.getAllCharacters()).thenReturn(new ArrayList<>());

        assertEquals(new ArrayList<>(),characterController.getCharacters().getBody());
    }

    @Test
    void getCharacterByIdTest() {
        Character character = new Character(3L,"pluto.jpg","pluto",8, 79.0, "A funny dog", new ArrayList<>());
        when(characterService.getCharacter(3L)).thenReturn(character);

        assertEquals(character, characterController.getCharacterById(3L).getBody());
    }

    @Test
    void getCharacterNotFoundTest() {
        assertNull(characterController.getCharacterById(5L).getBody());
    }

    @Test
    void getCharacterWithNullIdTest() {
        assertNull(characterController.getCharacterById(null).getBody());
    }

    @Test
    void createCharacterTest() {
        Character character = new Character(10L,"Goofy.jpg","Goofy",42, 82.0, "A funny dog", new ArrayList<>());
        when(characterService.saveCharacter(any(Character.class))).thenReturn(character);

        assertEquals(character, characterController.createCharacter(character).getBody());
    }

    @Test
    void createNullCharacterTest() {
        when(characterService.saveCharacter(null)).thenThrow(new IllegalArgumentException());;

        assertThrowsExactly(IllegalArgumentException.class , () -> characterController.createCharacter(null));
    }

    @Test
    void createCharacterWithIdTest() {
        when(characterService.saveCharacter(any(Character.class))).thenThrow(new IllegalArgumentException());
        Character character = new Character(10L,"Goofy.jpg","Goofy",42, 82.0, "A funny dog", new ArrayList<>());

        assertThrowsExactly(IllegalArgumentException.class ,() -> characterController.createCharacter(character));
    }

    @Test
    void updateCharacterTest() {
        Character character = new Character(10L,"Goofy.jpg","Goofy",42, 82.0, "A funny dog", new ArrayList<>());
        when(characterService.updateCharacter(character)).thenReturn(character);

        assertEquals(character, characterController.updateCharacter(character).getBody());
    }

    @Test
    void updateCharacterNotFoundTest() {
        Character character = new Character(100L,"Winnie.jpg","Winnie",45, 80.0, "A funny bear", new ArrayList<>());
        when(characterService.updateCharacter(character)).thenThrow(new IllegalArgumentException());

        assertThrowsExactly(IllegalArgumentException.class ,() -> characterController.updateCharacter(character));
    }

    @Test
    void updateCharacterWithNullIdTest() {
        Character character = new Character(null,"Winnie.jpg","Winnie",45, 80.0, "A funny bear", new ArrayList<>());
        when(characterService.updateCharacter(character)).thenThrow(new IllegalArgumentException());

        assertThrowsExactly(IllegalArgumentException.class ,() -> characterController.updateCharacter(character));
    }

    @Test
    void deleteCharacterTest() {
        assertEquals(HttpStatus.NO_CONTENT, characterController.deleteCharacter(4L).getStatusCode());
    }

    @Test
    void deleteCharacterNotFoundTest() {
        doThrow(new IllegalArgumentException()).when(characterService).deleteCharacter(291L);

        assertEquals(HttpStatus.NOT_FOUND, characterController.deleteCharacter(291L).getStatusCode());
    }
}