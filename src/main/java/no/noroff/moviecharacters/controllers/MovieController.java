package no.noroff.moviecharacters.controllers;

import no.noroff.moviecharacters.model.Movie;
import no.noroff.moviecharacters.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(movies, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        Movie returnMovie = new Movie();
        HttpStatus status;

        if (movieRepository.existsById(id)) {
            status = HttpStatus.OK;
            returnMovie = movieRepository.findById(id).get();
        }
        else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnMovie, status);
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie returnMovie = movieRepository.save(movie);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(returnMovie, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        Movie returnMovie = new Movie();
        HttpStatus status;

        if (!id.equals(movie.getId())) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnMovie, status);
        }
        returnMovie = movieRepository.save(movie);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(returnMovie, status);
    }
}
