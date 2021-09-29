package no.noroff.moviecharacters.controllers;

import no.noroff.moviecharacters.model.Actor;
import no.noroff.moviecharacters.model.Franchise;
import no.noroff.moviecharacters.model.Movie;
import no.noroff.moviecharacters.repositories.FranchiseRepository;
import no.noroff.moviecharacters.repositories.MovieRepository;
import no.noroff.moviecharacters.services.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/franchises")
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private FranchiseService franchiseService;


    @GetMapping
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        List<Franchise> franchises = franchiseRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(franchises, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Franchise> getFranchise(@PathVariable Long id) {
        Franchise returnFranchise = new Franchise();
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            status = HttpStatus.OK;
            returnFranchise = franchiseRepository.findById(id).get();
        }
        else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnFranchise, status);
    }

    @GetMapping("/{id}/movies")
    public ResponseEntity<Set<Movie>> getMoviesInFranchise(@PathVariable Long id) {
        Set<Movie> movies = null;
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            status = HttpStatus.OK;
            movies = franchiseService.getMoviesInFranchise(id);
        }
        else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(movies, status);
    }

    @GetMapping("/{id}/characters")
    public ResponseEntity<Set<Actor>> getCharactersInFranchise(@PathVariable Long id) {
        Set<Actor> actors = null;
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            status = HttpStatus.OK;
            actors = franchiseService.getAllCharactersInFranchise(id);
        }
        else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(actors, status);
    }

    @PostMapping
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise) {
        Franchise returnFranchise = franchiseRepository.save(franchise);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(returnFranchise, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Franchise> updateFranchise(@PathVariable Long id, @RequestBody Franchise franchise) {
        Franchise returnFranchise = new Franchise();
        HttpStatus status;

        if (!id.equals(franchise.getId())) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnFranchise, status);
        }
        returnFranchise = franchiseRepository.save(franchise);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(returnFranchise, status);
    }

    @PutMapping("/{id}/movies")
    public ResponseEntity<Franchise> updateMoviesInFranchise(@PathVariable Long id, @RequestBody List<Long> movieIds) {
        Franchise returnFranchise = new Franchise();
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            returnFranchise = franchiseService.updateMoviesInFranchise(id, movieIds);
            status = HttpStatus.OK;
        }
        else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(returnFranchise, status);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Franchise> deleteFranchise(@PathVariable Long id) {
        Franchise returnFranchise = new Franchise();
        HttpStatus status;

        if (!franchiseRepository.existsById(id)) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(returnFranchise, status);
        }
        franchiseRepository.deleteById(id);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(returnFranchise, status);
    }
}
