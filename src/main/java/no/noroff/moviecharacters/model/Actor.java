package no.noroff.moviecharacters.model;

import javax.persistence.*;
import java.util.Set;

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

    @ManyToMany(mappedBy = "actors")
    Set<Movie> movies;

}
