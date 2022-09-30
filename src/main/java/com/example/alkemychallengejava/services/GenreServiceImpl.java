package com.example.alkemychallengejava.services;

import com.example.alkemychallengejava.entities.Genre;
import com.example.alkemychallengejava.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService{
    @Autowired
    GenreRepository genreRepository;


    @Override
    public Genre getGenreById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }
}
