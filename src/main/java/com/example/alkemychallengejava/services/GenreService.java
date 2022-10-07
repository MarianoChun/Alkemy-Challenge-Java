package com.example.alkemychallengejava.services;

import com.example.alkemychallengejava.entities.Genre;


public interface GenreService {
    Genre getGenreById(Long id);

    Genre getGenreByName(String name);

    boolean genreExists(String name);
}
