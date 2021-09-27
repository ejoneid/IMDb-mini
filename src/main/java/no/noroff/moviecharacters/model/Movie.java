package no.noroff.moviecharacters.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="genre", nullable = false)
    private String genre; //Lists gave me an error, has this as a string for now.

    @Column(name="year", nullable = false)
    private int year;

    @Column(name="director", nullable = false)
    private String director;

    @Column(name="picture")
    private String picture;

    @Column(name="trailer")
    private String trailer;

    // References to other tables

  /*

    // Ble brått usikker på de to under her som har med actor å gjøre, om det skal være
    // one to many eller many to many. Lar derfor begge stå bare.
    @OneToMany
    @JoinColumn(name="actor_id")
    List<Actor> actors;

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = {@JoinColumn(name="actor_id")},
            inverseJoinColumns = {@JoinColumn(name="movie_id")}
    )
    public List<Actor> actors;

    @ManyToOne
    @JoinColumn(name="franchise_id")
    private Franchise franchise;
*/
}
