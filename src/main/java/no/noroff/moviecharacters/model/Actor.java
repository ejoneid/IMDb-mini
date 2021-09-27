package no.noroff.moviecharacters.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="alias")
    private String alias;

    @Column(name="gender")
    private String gender;

    @Column(name="picture")
    private String picture;

    // References to other tables
/*

    // Ble brått usikker på om det skal være mange til mange eller en til mange med actor/movie
    // Lar begge stå bare.
    @OneToMany
    @JoinColumn(name="movie_id")
    List<Movie> movies;

    @ManyToMany(mappedBy = "movies")
    public List<Movie> movies;
*/
}
