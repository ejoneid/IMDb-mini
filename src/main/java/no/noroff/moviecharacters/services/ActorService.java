package no.noroff.moviecharacters.services;

import no.noroff.moviecharacters.model.Actor;
import no.noroff.moviecharacters.model.Movie;
import no.noroff.moviecharacters.repositories.ActorRepository;
import no.noroff.moviecharacters.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ActorService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ActorRepository actorRepository;

    public void deleteMovieReferences(long id) {
        Actor character = actorRepository.findById(id).get();
        Set<Movie> movies = character.getMovies();

        for (Movie movie : movies) {
            movie.getActors().remove(character);
        }

        movieRepository.saveAll(movies);
    }

}