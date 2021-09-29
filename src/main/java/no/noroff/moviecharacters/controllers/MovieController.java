package no.noroff.moviecharacters.controllers;

import no.noroff.moviecharacters.model.Actor;
import no.noroff.moviecharacters.model.Movie;
import no.noroff.moviecharacters.repositories.ActorRepository;
import no.noroff.moviecharacters.repositories.MovieRepository;
import no.noroff.moviecharacters.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

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

    @GetMapping("/{id}/characters")
    public ResponseEntity<Set<Actor>> getCharactersInMovie(@PathVariable Long id)throws Exception {
        Set<Actor> actors = null;
        HttpStatus status;

        if(movieRepository.existsById(id)) {
            status = HttpStatus.OK;
            actors = movieService.getCharactersInMovie(id);
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(actors, status);
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

    @PutMapping("/{id}/characters")
    public ResponseEntity<Movie> updateCharactersInMovie(@PathVariable Long id, @RequestBody List<Long> characterIds) {
        Movie returnMovie = new Movie();
        HttpStatus status;

        if (movieRepository.existsById(id)) {
            returnMovie = movieService.updateCharactersInMovie(id, characterIds);
            status = HttpStatus.NO_CONTENT;
        }
        else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(returnMovie, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable Long id) {
        Movie returnMovie = new Movie();
        HttpStatus status;

        if (!movieRepository.existsById(id)) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(returnMovie, status);
        }
        movieRepository.deleteById(id);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(returnMovie, status);
    }
}
