package com.example.alkemychallengejava.controllers;

import com.example.alkemychallengejava.entities.Character;
import com.example.alkemychallengejava.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @GetMapping("/api/characters")
    public ResponseEntity<Iterable<Character>> getCharacters(){
        return ResponseEntity.ok(characterService.getAllCharacters());
    }

    @PostMapping("/api/characters")
    public ResponseEntity<Character> createCharacter(@RequestBody Character character){
        return ResponseEntity.ok(characterService.saveCharacter(character));
    }

    @PutMapping("/api/characters")
    public ResponseEntity<Character> updateCharacter(@RequestBody Character character){
        return ResponseEntity.ok(characterService.modifyCharacter(character));
    }

    @DeleteMapping("/api/characters/{id}")
    public ResponseEntity<Character> deleteCharacter(@PathVariable Long id){
        characterService.deleteCharacter(id);

        return ResponseEntity.noContent().build();
    }
}
