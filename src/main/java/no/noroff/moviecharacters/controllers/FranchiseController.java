package no.noroff.moviecharacters.controllers;

import no.noroff.moviecharacters.model.Franchise;
import no.noroff.moviecharacters.repositories.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/franchises")
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @GetMapping
    public ResponseEntity<List<Franchise>> getAllMovies() {
        List<Franchise> movies = franchiseRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(movies, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Franchise> getMovie(@PathVariable Long id) {
        Franchise returnMovie = new Franchise();
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            status = HttpStatus.OK;
            returnMovie = franchiseRepository.findById(id).get();
        }
        else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnMovie, status);
    }

    @PostMapping
    public ResponseEntity<Franchise> addMovie(@RequestBody Franchise franchise) {
        Franchise returnMovie = franchiseRepository.save(franchise);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(returnMovie, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Franchise> updateMovie(@PathVariable Long id, @RequestBody Franchise franchise) {
        Franchise returnMovie = new Franchise();
        HttpStatus status;

        if (!id.equals(franchise.getId())) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnMovie, status);
        }
        returnMovie = franchiseRepository.save(franchise);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(returnMovie, status);
    }
}
