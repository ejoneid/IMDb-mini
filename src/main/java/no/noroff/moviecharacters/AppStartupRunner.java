package no.noroff.moviecharacters;

import no.noroff.moviecharacters.model.Actor;
import no.noroff.moviecharacters.model.Franchise;
import no.noroff.moviecharacters.model.Movie;
import no.noroff.moviecharacters.repositories.ActorRepository;
import no.noroff.moviecharacters.repositories.FranchiseRepository;
import no.noroff.moviecharacters.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

@Component
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private FranchiseRepository franchiseRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ActorRepository actorRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        ////////////////////
        //   FRANCHISES   //
        ////////////////////

        Franchise harryPotter = new Franchise(1, "Harry Potter", "All the Harry Potter movies are based off of J.K Rowlings best selling Harry potter book-series.");
        Franchise lordOfTheRings = new Franchise(2, "The Lord of the Rings", "Lord of the Rings are written by J.R.R Tolkien");

        franchiseRepository.saveAll(Arrays.asList(harryPotter, lordOfTheRings));

        ////////////////////
        //    ACTORS      //
        ////////////////////

        // HARRY POTTER
        Actor danielRadcliffe = new Actor(1, "Daniel Radcliffe", "Dan Radcliffe", "Male", "https://m.media-amazon.com/images/M/MV5BZmE0NzNiNzQtYTVlYS00MjljLWE4MTgtYzYxNjU2NjZkM2M4XkEyXkFqcGdeQXVyNjY5NDgzNjQ@._V1_UX214_CR0,0,214,317_AL_.jpg");
        Actor rupertGrint = new Actor(2, "Rupert Grint", "", "Male", "https://m.media-amazon.com/images/M/MV5BMjI3MDA3NjA1N15BMl5BanBnXkFtZTcwMDcyMDYzNw@@._V1_UX214_CR0,0,214,317_AL_.jpg");
        Actor emmaWatson = new Actor(3, "Emma Watson", "", "Female", "https://m.media-amazon.com/images/M/MV5BMTQ3ODE2NTMxMV5BMl5BanBnXkFtZTgwOTIzOTQzMjE@._V1_UY317_CR21,0,214,317_AL_.jpg");

        // LORD OF THE RINGS
        Actor elijahWood = new Actor(4, "Elijah Wood", "Eli Wood", "Male", "https://m.media-amazon.com/images/M/MV5BMTM0NDIxMzQ5OF5BMl5BanBnXkFtZTcwNzAyNTA4Nw@@._V1_UX214_CR0,0,214,317_AL_.jpg");
        Actor ianMcKellen = new Actor(5, "Ian McKellen", "Ian", "Male", "https://m.media-amazon.com/images/M/MV5BMTQ2MjgyNjk3MV5BMl5BanBnXkFtZTcwNTA3NTY5Mg@@._V1_UY317_CR10,0,214,317_AL_.jpg");
        Actor cateBlanchett = new Actor(6, "Cate Blanchett", "", "Female", "https://m.media-amazon.com/images/M/MV5BMTc1MDI0MDg1NV5BMl5BanBnXkFtZTgwMDM3OTAzMTE@._V1_UY317_CR3,0,214,317_AL_.jpg");

        // BOTH MOVIE FRANCHISES (Olivander and Voice of Aragorn in Animated Lord of the Rings in 1978)
        Actor johnHurt = new Actor(7, "John Hurt", "", "Male", "https://m.media-amazon.com/images/M/MV5BMTM1NTgyMTAyOV5BMl5BanBnXkFtZTcwMTE4MjQwNA@@._V1_UY317_CR5,0,214,317_AL_.jpg");

        actorRepository.saveAll(Arrays.asList(danielRadcliffe, rupertGrint, emmaWatson, elijahWood, ianMcKellen, cateBlanchett, johnHurt));

        ////////////////////
        //    MOVIES      //
        ////////////////////

        // HARRY POTTER
        Movie hpPhilosophersStone = new Movie(1, "Harry Potter and the Philosopher's Stone", "Fantasy", 2001, "Chris Columbus", "", "https://www.youtube.com/watch?v=VyHV0BRtdxo", Set.of(danielRadcliffe, emmaWatson, rupertGrint), harryPotter);
        Movie hpChamberOfSecrets = new Movie(2, "Harry Potter and the Chamber of Secrets", "Fantasy", 2002, "Chris Columbus", "", "https://www.youtube.com/watch?v=1bq0qff4iF8", Set.of(danielRadcliffe, emmaWatson, rupertGrint), harryPotter);
        Movie hpDeathlyHallowsOne = new Movie(3, "Harry Potter and the Deathly Hallows: Part 1", "Fantasy", 2010, "David Yates", "", "https://www.youtube.com/watch?v=MxqsmsA8y5k", Set.of(danielRadcliffe, emmaWatson, rupertGrint), harryPotter);
        Movie hpDeathlyHallowsTwo = new Movie(4, "Harry Potter and the Deathly Hallows: Part 2", "Fantasy", 2011, "David Yates", "", "https://www.youtube.com/watch?v=mObK5XD8udk", Set.of(danielRadcliffe, emmaWatson, rupertGrint), harryPotter);

        // LORD OF THE RINGS
        Movie lotrAnimated = new Movie(5, "The Lord of the Rings", "Animation, Fantasy", 1978, "Ralph Bakshi", "", "https://www.youtube.com/watch?v=Y46VsU2RhrM", Set.of(johnHurt), lordOfTheRings);
        Movie lotrFellowshipOfTheRing = new Movie(6, "The Lord of the Rings: Fellowship of the Ring", "Fantasy", 2001, "Peter Jackson", "", "https://www.youtube.com/watch?v=V75dMMIW2B4", Set.of(elijahWood, ianMcKellen, cateBlanchett), lordOfTheRings);
        Movie lotrTheTwoTowers = new Movie(7, "The Lord of the Rings: The Two Towers", "Fantasy", 2002, "Peter Jackson", "", "https://www.youtube.com/watch?v=LbfMDwc4azU", Set.of(elijahWood, ianMcKellen, cateBlanchett), lordOfTheRings);
        Movie lotrReturnOfTheKing = new Movie(8, "The Lord of the Rings: Return of the King", "Fantasy", 2003, "Peter Jackson", "", "https://www.youtube.com/watch?v=LbfMDwc4azU", Set.of(elijahWood, ianMcKellen, cateBlanchett), lordOfTheRings);

        hpPhilosophersStone.setFranchise(harryPotter);
        lotrAnimated.setFranchise(lordOfTheRings);
        lotrAnimated.setActors(Set.of(johnHurt));

        // SAVE
        movieRepository.saveAll(Arrays.asList(hpPhilosophersStone, hpChamberOfSecrets, hpDeathlyHallowsOne, hpDeathlyHallowsTwo, lotrAnimated, lotrFellowshipOfTheRing, lotrTheTwoTowers, lotrReturnOfTheKing));

    }
}
