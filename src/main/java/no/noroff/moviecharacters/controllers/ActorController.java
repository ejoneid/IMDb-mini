package no.noroff.moviecharacters.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.moviecharacters.model.Actor;
import no.noroff.moviecharacters.model.Movie;
import no.noroff.moviecharacters.repositories.ActorRepository;
import no.noroff.moviecharacters.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/characters")
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ActorService actorService;

    @Operation(summary = "Get all characters")
    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors() {
        List<Actor> actors = actorRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(actors, status);
    }


    @Operation(summary = "Get a character by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the character",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Actor.class)) }),
            @ApiResponse(responseCode = "404", description = "Did not find the character",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActor(@PathVariable Long id) {
        Actor returnActor = new Actor();
        HttpStatus status;

        if (actorRepository.existsById(id)) {
            status = HttpStatus.OK;
            returnActor = actorRepository.findById(id).get();
        }
        else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnActor, status);
    }


    @Operation(summary = "Add a character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the character",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Actor.class)) }),
    })
    @PostMapping
    public ResponseEntity<Actor> addActor(@RequestBody Actor actor) {
        Actor responseActor = actorRepository.save(actor);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(responseActor, status);
    }


    @Operation(summary = "Update a character by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Updated the character"),
            @ApiResponse(responseCode = "400", description = "Request contains bad syntax",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Did not find the character",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable Long id, @RequestBody Actor actor) {
        Actor returnActor = new Actor();
        HttpStatus status;

        if (!id.equals(actor.getId())) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnActor, status);
        }
        if (!actorRepository.existsById(id)) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(returnActor, status);
        }
        returnActor = actorRepository.save(actor);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(returnActor, status);
    }


    @Operation(summary = "Delete a character by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted the character"),
            @ApiResponse(responseCode = "404", description = "Did not find the character",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Actor> deleteActor(@PathVariable Long id) {
        Actor returnActor = new Actor();
        HttpStatus status;

        if (!actorRepository.existsById(id)) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(returnActor, status);
        }
        actorService.deleteMovieReferences(id);
        actorRepository.deleteById(id);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(returnActor, status);
    }
}
