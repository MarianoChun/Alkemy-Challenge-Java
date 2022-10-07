package com.example.alkemychallengejava.services;

import com.example.alkemychallengejava.entities.Character;
import com.example.alkemychallengejava.entities.Movie;


public interface MovieService {
    Movie getMovie(Long id);

    Iterable<Movie> getAllMovies();

    Movie saveMovie(Movie movie);

    Movie updateMovie(Movie movie);

    void deleteMovie(Long id);

    Movie findByTitle(String title);

    Movie findById(Long idMovie);
    Iterable<Movie> filterByGenre(Long idGenre);

    Iterable<Movie> sortByCreationDate(String sortType);

    boolean existsMovie(String title);

}
