package com.example.alkemychallengejava.services;

import com.example.alkemychallengejava.entities.Personaje;
import org.springframework.stereotype.Service;

public interface PersonajeService {

    Personaje getPersonaje(Long id);

    Iterable<Personaje> getAllPersonajes();

    Personaje savePersonaje(Personaje personaje);

    Personaje modifyPersonaje(Personaje personaje);

    void deletePersonaje(Long id);

}
