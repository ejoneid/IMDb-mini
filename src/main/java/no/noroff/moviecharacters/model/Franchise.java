package no.noroff.moviecharacters.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="franchise")
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description")
    private String description;

    //References to other tables
/*
    @OneToMany(mappedBy = "movie")
    List<Movie> movies;
*/
}
