package no.noroff.moviecharacters.repositories;

import no.noroff.moviecharacters.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {

}
