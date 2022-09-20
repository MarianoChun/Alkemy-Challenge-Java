package com.example.alkemychallengejava.repository;

import com.example.alkemychallengejava.entities.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
}
