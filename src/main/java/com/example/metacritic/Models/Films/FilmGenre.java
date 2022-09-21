package com.example.metacritic.Models.Films;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class FilmGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(unique = true)
    @NotNull
    @NotEmpty(message = "Поле ЖАНРА не может быть пустым")
    @Size(min=3,max=25,message = "Значение поле должно быть в диапазоне от 3 до 25 символов")
    private String genreName;

    /*Связь один ко многим с таблицей фильмов*/
    @OneToMany(mappedBy = "filmGenre", cascade = CascadeType.ALL)//
    private Collection<Films> films;
    public Collection<Films> getFilms() {
        return films;
    }
    public void setFilms(Collection<Films> films) {
        this.films = films;
    }
    /**/


    public FilmGenre(String genre) {
        this.genreName = genre;
    }//конструктор
    public FilmGenre() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getGenreName() {
        return genreName;
    }
    public void setGenreName(String genre) {
        this.genreName = genre;
    }
}
