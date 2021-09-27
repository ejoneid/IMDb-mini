package no.noroff.moviecharacters.controllers;

import no.noroff.moviecharacters.model.Actor;
import no.noroff.moviecharacters.model.Movie;
import no.noroff.moviecharacters.repositories.ActorRepository;
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

    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors() {
        List<Actor> actors = actorRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(actors, status);
    }

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

    @PostMapping
    public ResponseEntity<Actor> addActor(@RequestBody Actor actor) {
        Actor responseActor = actorRepository.save(actor);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(responseActor, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable Long id, @RequestBody Actor actor) {
        Actor returnActor = new Actor();
        HttpStatus status;

        if (!id.equals(actor.getId())) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnActor, status);
        }
        returnActor = actorRepository.save(actor);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(returnActor, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Actor> deleteActor(@PathVariable Long id) {
        Actor returnActor = new Actor();
        HttpStatus status;

        if (!actorRepository.existsById(id)) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(returnActor, status);
        }
        actorRepository.deleteById(id);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(returnActor, status);
    }
}
