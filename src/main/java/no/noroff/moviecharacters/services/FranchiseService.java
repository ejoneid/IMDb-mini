package no.noroff.moviecharacters.services;

import no.noroff.moviecharacters.model.Actor;
import no.noroff.moviecharacters.model.Franchise;
import no.noroff.moviecharacters.model.Movie;
import no.noroff.moviecharacters.repositories.FranchiseRepository;
import no.noroff.moviecharacters.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;
    @Autowired
    private MovieRepository movieRepository;


    public Set<Movie> getMoviesInFranchise(Long id) {
        return franchiseRepository.findById(id).get().getMovies();
    }

    public Set<Actor> getAllCharactersInFranchise(Long id) {
        Set<Movie> movies;
        Set<Actor> actorSet = new java.util.HashSet<>(Collections.emptySet());

        movies = getMoviesInFranchise(id);
        for (Movie movie : movies) {
            actorSet.addAll(movie.getActors());
        }
        return actorSet;
    }

    public Franchise updateMoviesInFranchise(Long id, List<Long> movieIds) {
        Franchise franchise = franchiseRepository.findById(id).get();
        List<Movie> moviesToAdd = movieRepository.findAllById(movieIds);
        for (Movie movie : moviesToAdd) {
            movie.setFranchise(franchise);
        }
        franchise.getMovies().addAll(moviesToAdd);
        franchiseRepository.save(franchise);
        return franchise;
    }

    public void deleteMoviereferences(Long id) {
        Franchise franchise = franchiseRepository.findById(id).get();
        Set<Movie> movies = franchise.getMovies();

        for (Movie movie : movies) {
            movie.setFranchise(null);
        }

        movieRepository.saveAll(movies);
    }
}
