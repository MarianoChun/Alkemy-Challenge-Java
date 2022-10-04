package com.example.alkemychallengejava.services;

import com.example.alkemychallengejava.entities.Genre;
import com.example.alkemychallengejava.entities.Movie;
import com.example.alkemychallengejava.exception.ErrorMessage;
import com.example.alkemychallengejava.repository.MovieRepository;
import com.example.alkemychallengejava.utils.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    GenreService genreService;

    @Override
    public Movie getMovie(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        return movieOptional.orElse(null);
    }

    @Override
    public Iterable<Movie> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.isEmpty() ? new ArrayList<>() : movies;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        if(movie.getId() != null){
            throw new IllegalArgumentException(ErrorMessage.RESOURCE_HAS_ID.getMessage());
        }

        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        if(movie.getId() == null){
            throw new IllegalArgumentException(ErrorMessage.RESOURCE_HAS_NO_ID.getMessage());
        }

        if(movieRepository.findById(movie.getId()).isEmpty()){
            throw new IllegalArgumentException(ErrorMessage.RESOURCE_NOT_FOUND.getMessage());
        }

        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long id) {
        if(movieRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException((ErrorMessage.RESOURCE_NOT_FOUND.getMessage()));
        }

        movieRepository.deleteById(id);
    }

    @Override
    public Movie findByTitle(String title) {
        Optional<Movie> movie = movieRepository.findAll().stream()
                .filter(actualMovie -> actualMovie.getTitle().equals(title)).findFirst();

        return movie.orElse(null);
    }

    @Override
    public Iterable<Movie> filterByGenre(Long idGenre) {
        Genre genre = genreService.getGenreById(idGenre);

        return genre.getMoviesAssociated();
    }

    @Override
    public Iterable<Movie> sortByCreationDate(String sortType) {
        DateParser dateParser = new DateParser();

        if(sortType.equals("ASC")){
            return movieRepository.findAll().stream()
                    .sorted((movie1, movie2) -> (int) (dateParser.convertStringToDate(movie1.getCreationDate()).getTime()
                            - dateParser.convertStringToDate(movie2.getCreationDate()).getTime()))
                    .toList();
        }
        if (sortType.equals("DESC")) {
            return movieRepository.findAll().stream()
                    .sorted((movie1, movie2) -> (int) (dateParser.convertStringToDate(movie2.getCreationDate()).getTime() -
                            dateParser.convertStringToDate(movie1.getCreationDate()).getTime()))
                    .toList();
        }

        throw new IllegalArgumentException("Insert a valid sort type: ASC or DESC");
    }
}
