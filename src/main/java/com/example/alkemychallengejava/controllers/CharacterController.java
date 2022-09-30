package com.example.alkemychallengejava.controllers;

import com.example.alkemychallengejava.DTO.CharacterDTO;
import com.example.alkemychallengejava.entities.Character;
import com.example.alkemychallengejava.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @GetMapping("/api/characters")
    public ResponseEntity<Iterable<CharacterDTO>> getCharacters(){

        Stream<CharacterDTO> characterDTOStream = StreamSupport.stream(characterService.getAllCharacters().spliterator(),false)
                .map(character -> new CharacterDTO(character.getId(), character.getImage(), character.getName()));

        return ResponseEntity.ok(characterDTOStream.toList());
    }

    @GetMapping(value = "/api/characters", params = "age")
    public  ResponseEntity<Iterable<Character>> filterByAge(@RequestParam Integer age){
        return ResponseEntity.ok(characterService.filterByAge(age));
    }

    @GetMapping(value = "/api/characters", params = "weight")
    public ResponseEntity<Iterable<Character>> filterByWeight(@RequestParam Double weight){
        return ResponseEntity.ok(characterService.filterByWeight(weight));
    }

//    @GetMapping(value = "/api/characters", params = "idMovie")
//    public ResponseEntity<Iterable<CharacterDTO>> filterByMovie(){
//        List<Character> characterList = characterService.filterByMovie();
//
//        return ResponseEntity.ok(characterDTOStream.toList());
//    }

    @GetMapping(value = "/api/characters", params = "name")
    public ResponseEntity<Character> getCharacterByName(@RequestParam String name){
        Character character = characterService.findByName(name);
        if(character == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(character);
    }

    @GetMapping("/api/characters/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id){

        return ResponseEntity.ok(characterService.getCharacter(id));
    }

    @PostMapping("/api/characters")
    public ResponseEntity<Character> createCharacter(@RequestBody Character character){
        return ResponseEntity.ok(characterService.saveCharacter(character));
    }

    @PutMapping("/api/characters")
    public ResponseEntity<Character> updateCharacter(@RequestBody Character character){
        return ResponseEntity.ok(characterService.updateCharacter(character));
    }

    @DeleteMapping("/api/characters/{id}")
    public ResponseEntity<Character> deleteCharacter(@PathVariable Long id){
       try {
           characterService.deleteCharacter(id);
       }catch (IllegalArgumentException e){
           return ResponseEntity.notFound().build();
       }

        return ResponseEntity.noContent().build();
    }
}
