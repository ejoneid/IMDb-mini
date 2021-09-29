package no.noroff.moviecharacters.services;

import no.noroff.moviecharacters.model.Actor;
import no.noroff.moviecharacters.model.Franchise;
import no.noroff.moviecharacters.model.Movie;
import no.noroff.moviecharacters.repositories.ActorRepository;
import no.noroff.moviecharacters.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ActorRepository actorRepository;

    public Set<Actor> getCharactersInMovie(long id) {
        return movieRepository.findById(id).get().getActors();
    }

    public Movie updateCharactersInMovie(Long id, List<Long> characterIds) {
        Movie movie = movieRepository.findById(id).get();
        List<Actor> characterToAdd = actorRepository.findAllById(characterIds);
        movie.getActors().addAll(characterToAdd);
        movieRepository.save(movie);
        return movie;
    }

}
