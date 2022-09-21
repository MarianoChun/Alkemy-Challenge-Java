package com.example.alkemychallengejava.controllers;

import com.example.alkemychallengejava.entities.Personaje;
import com.example.alkemychallengejava.services.PersonajeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonajeController {

    @Autowired
    PersonajeService personajeService;

    @GetMapping("/api/characters")
    public ResponseEntity<Iterable<Personaje>> getCharacters(){
        return ResponseEntity.ok(personajeService.getAllPersonajes());
    }

    @PostMapping("/api/characters")
    public ResponseEntity<Personaje> createCharacter(@RequestBody Personaje personaje){
        return ResponseEntity.ok(personajeService.savePersonaje(personaje));
    }

    @PutMapping("/api/characters")
    public ResponseEntity<Personaje> updateCharacter(@RequestBody Personaje personaje){
        return ResponseEntity.ok(personajeService.modifyPersonaje(personaje));
    }

    @DeleteMapping("/api/characters/{id}")
    public ResponseEntity<Personaje> deleteCharacter(@PathVariable Long id){
        personajeService.deletePersonaje(id);

        return ResponseEntity.noContent().build();
    }
}
