package no.noroff.moviecharacters.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
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

    @JsonGetter("movies")
    public List<String> movies() {
        if (movies != null) {
            return movies.stream().map(movie -> {
                return "/api/v1/movies/" + movie.getId();
            }).collect(Collectors.toList());
        }
        return null;
    }

    public Actor(long id, String name, String alias, String gender, String picture) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.gender = gender;
        this.picture = picture;
    }

    public Actor() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
