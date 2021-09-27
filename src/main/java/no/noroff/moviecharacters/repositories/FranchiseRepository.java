package no.noroff.moviecharacters.repositories;

import no.noroff.moviecharacters.model.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
}
