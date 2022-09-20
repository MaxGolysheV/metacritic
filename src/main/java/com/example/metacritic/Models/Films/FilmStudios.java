package com.example.metacritic.Models.Films;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class FilmStudios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty(message = "Поле НАЗВАНИЕ СТУДИИ не может быть пустым")
    private String filmStudioName;

    /*Связь один ко многим с таблицей фильмов*/
    @OneToMany(mappedBy = "filmStudio", cascade = CascadeType.ALL)
    private Collection<Films> films;
    public Collection<Films> getFilms() {
        return films;
    }
    public void setFilms(Collection<Films> films) {
        this.films = films;
    }
    /**/
    public FilmStudios() {
    }
    public FilmStudios(String filmStudioName) {
        this.filmStudioName = filmStudioName;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFilmStudioName() {
        return filmStudioName;
    }
    public void setFilmStudioName(String filmStudioName) {
        this.filmStudioName = filmStudioName;
    }
}
