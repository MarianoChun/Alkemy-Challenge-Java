package com.example.alkemychallengejava.services;

import com.example.alkemychallengejava.entities.Personaje;
import com.example.alkemychallengejava.repository.PersonajeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PersonajeServiceImpl implements PersonajeService{

    @Autowired
    PersonajeRepository personajeRepository;

    @Override
    public Personaje getPersonaje(Long id) {
        return personajeRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Personaje> getAllPersonajes() {
        if(personajeRepository.findAll().isEmpty()){
            return new ArrayList<>();
        }

        return personajeRepository.findAll();
    }

    @Override
    public Personaje savePersonaje(Personaje personaje) {
        if(personaje.getId() != null){
            throw new IllegalArgumentException("Error: El personaje a crear tiene id");
        }

        return personajeRepository.save(personaje);
    }

    @Override
    public Personaje modifyPersonaje(Personaje personaje) {
        if(personaje.getId() == null){
            throw new IllegalArgumentException("Error: El personaje a modificar no tiene id");
        }

        if(personajeRepository.findById(personaje.getId()).isEmpty()){
            throw new IllegalArgumentException("Error: El personaje a modificar no existe");
        }

        return personajeRepository.save(personaje);
    }

    @Override
    public void deletePersonaje(Long id) {
        if(personajeRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("Error: El personaje a eliminar no existe/ya fue eliminado");
        }

        personajeRepository.deleteById(id);
    }
}
