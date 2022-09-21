package com.example.metacritic.Models.Films;


import com.example.metacritic.Models.Age;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Films {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    @NotNull
    @NotEmpty(message = "Поле НАЗВАНИЕ ФИЛЬМА не может быть пустым")
    @Size(min=3,max=25,message = "Значение поле должно быть в диапазоне от 3 до 25 символов")
    private String filmName;

    /*Связь многие к одному с таблицей студий фильма*/
    @NotNull
   // @ManyToOne(optional = true, cascade = CascadeType.DETACH)//
    @ManyToOne(optional = true)//
    private FilmStudios filmStudio;
    public FilmStudios getFilmStudio() {
        return filmStudio;
    }
    public void setFilmStudio(FilmStudios filmStudio) {
        this.filmStudio = filmStudio;
    }
    /**/

    /*Связь многие к одному  таблицей жанров фильма*/
    @NotNull
    @ManyToOne(optional = true)
    private FilmGenre filmGenre;
    public FilmGenre getFilmGenre() {
        return filmGenre;
    }
    public void setFilmGenre(FilmGenre filmGenre) {
        this.filmGenre = filmGenre;
    }
    /**/

    /*Связь многие к одному с таблицей возраста*/

    @NotNull
    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Age age;
    public Age getAge() {
        return age;
    }
    public void setAge(Age age) {
        this.age = age;
    }
    /**/

    public Films(String filmName, FilmStudios filmStudio, FilmGenre filmGenre, Age age) {
        this.filmName = filmName;
        this.filmStudio = filmStudio;
        this.filmGenre = filmGenre;
        this.age = age;
    }

    public Films() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }
}
