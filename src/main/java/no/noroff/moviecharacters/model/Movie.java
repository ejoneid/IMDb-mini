package no.noroff.moviecharacters.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name="title", nullable = false, length = 200)
    private String title;

    @NotNull
    @Column(name="genre", nullable = false, length = 100)
    private String genre; //Lists gave me an error, has this as a string for now.

    @NotNull
    @Column(name="year", nullable = false)
    private int year;

    @NotNull
    @Column(name="director", nullable = false, length = 100)
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

    @JsonGetter("characters")
    public List<String> actors() {
        if (actors != null) {
            return actors.stream().map(actor -> {
                return "/api/v1/characters/" + actor.getId();
            }).collect(Collectors.toList());
        }
        return null;
    }

    @ManyToOne
    @JoinColumn(name="franchise_id")
    private Franchise franchise;

    @JsonGetter("franchise")
    public String franchise() {
        if (franchise != null) {
            return "/api/v1/franchises/" + franchise.getId();
        }
        return null;
    }

    public Movie(String title, String genre, int year, String director, String picture, String trailer, Set<Actor> actors, Franchise franchise) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.director = director;
        this.picture = picture;
        this.trailer = trailer;
        this.actors = actors;
        this.franchise = franchise;
    }

    public Movie() {

    }


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
