package com.example.alkemychallengejava.controllers;

import com.example.alkemychallengejava.DTO.MovieDTO;
import com.example.alkemychallengejava.entities.Movie;
import com.example.alkemychallengejava.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping("api/movies/{id}")
    public ResponseEntity<Movie> findMovieById(@PathVariable Long id){
        return ResponseEntity.ok(movieService.getMovie(id));
    }

    @GetMapping(value = "api/movies", params = "title")
    public ResponseEntity<Movie> findMovieByTitle(@RequestParam String title){
        return ResponseEntity.ok(movieService.findByTitle(title));
    }

    @GetMapping(value = "api/movies", params = "idGenre")
    public ResponseEntity<Iterable<Movie>> filterMoviesByGenre(@RequestParam Long idGenre){
        return ResponseEntity.ok(movieService.filterByGenre(idGenre));
    }

    @GetMapping(value = "api/movies", params = "order")
    public ResponseEntity<Iterable<Movie>> sortMoviesByCreationDate(@RequestParam String order){
        return ResponseEntity.ok(movieService.sortByCreationDate(order));
    }

    @GetMapping("api/movies")
    public ResponseEntity<Iterable<MovieDTO>> getMovies(){
        Stream<MovieDTO> movieStreamDTO = StreamSupport.stream(movieService.getAllMovies().spliterator(), false)
                .map(movie -> new MovieDTO(movie.getId(), movie.getImage(), movie.getTitle(), movie.getCreationDate()));
        return ResponseEntity.ok(movieStreamDTO.toList());
    }

    @PostMapping("api/movies")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
        return ResponseEntity.ok(movieService.saveMovie(movie));
    }

    @PutMapping("api/movies")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie){
        return ResponseEntity.ok(movieService.updateMovie(movie));
    }

    @DeleteMapping("api/movies/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable Long id){
        try {
            movieService.deleteMovie(id);
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
