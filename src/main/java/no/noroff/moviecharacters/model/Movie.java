package no.noroff.moviecharacters.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = {@JoinColumn(name="actor_id")},
            inverseJoinColumns = {@JoinColumn(name="movie_id")}
    )
    Set<Actor> actors;

    @ManyToOne
    @JoinColumn(name="franchise_id")
    private Franchise franchise;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Franchise getFranchise() {
        return franchise;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }
}
