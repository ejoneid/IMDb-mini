package no.noroff.moviecharacters.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="franchise")
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    //References to other tables

    @OneToMany(mappedBy = "franchise")
    List<Movie> movies;

    @JsonGetter("movies")
    public List<String> movies() {
        if (movies != null) {
            return movies.stream().map(movie -> {
                return "/api/v1/movies/" + movie.getId();
            }).collect(Collectors.toList());
        }
        return null;
    }

    public Franchise(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Franchise() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
