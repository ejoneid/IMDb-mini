package no.noroff.moviecharacters.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.moviecharacters.model.Franchise;
import no.noroff.moviecharacters.model.Movie;
import no.noroff.moviecharacters.repositories.FranchiseRepository;
import no.noroff.moviecharacters.repositories.MovieRepository;
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

    @Autowired
    private MovieRepository movieRepository;


    @Operation(summary = "Get all franchises")
    @GetMapping
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        List<Franchise> franchises = franchiseRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(franchises, status);
    }


    @Operation(summary = "Get a franchises by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the franchises",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Franchise.class)) }),
            @ApiResponse(responseCode = "404", description = "Did not find the franchise",
                    content = @Content),
    })
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


    @Operation(summary = "Add a franchises")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the character",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Franchise.class)) }),
            @ApiResponse(responseCode = "400", description = "Request contains bad syntax",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise) {
        Franchise returnFranchise = franchiseRepository.save(franchise);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(returnFranchise, status);
    }


    @Operation(summary = "Update a franchise by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Updated the character",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Request contains bad syntax",
                    content = @Content),
    })
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


    @Operation(summary = "Updates the movies in a franchise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Updated movies in the franchise successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Franchise.class)) }),
            @ApiResponse(responseCode = "400", description = "Request contains bad syntax",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Did not find the franchise",
                    content = @Content),
    })
    @PutMapping("/{id}/movies")
    public ResponseEntity<Franchise> updateMoviesInFranchise(@PathVariable Long id, @RequestBody List<Long> movieIds) {
        Franchise returnFranchise = new Franchise();
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            Franchise franchise = franchiseRepository.getById(id);
            List<Movie> moviesToAdd = movieRepository.findAllById(movieIds);
            franchise.getMovies().addAll(moviesToAdd);
            returnFranchise = franchiseRepository.save(franchise);
            status = HttpStatus.NO_CONTENT;
        }
        else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(returnFranchise, status);
    }


    @Operation(summary = "Delete a franchise by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted the franchise successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Request contains bad syntax",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Did not find the franchise",
                    content = @Content),
    })
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
