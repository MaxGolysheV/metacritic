package com.example.metacritic.Models;


import com.example.metacritic.Models.Films.Films;
import com.example.metacritic.Models.Games.Games;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Age {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Значение поля ВОЗРАСТ не может быть пустым")
    @NotEmpty(message = "Значение поля ВОЗРАСТ не может быть пустым")
    @Column(unique = true)
    private String value;

    /*Связь один ко многим с таблиццей игр*/
    @OneToMany(mappedBy = "age", cascade = CascadeType.ALL)
    private Collection<Games> games;
    public Collection<Games> getGames() {
        return games;
    }
    public void setGames(Collection<Games> games) {
        this.games = games;
    }
    /**/


    /*Связь один ко многим с таблицей фильмов*/
    @OneToMany(mappedBy = "age", cascade = CascadeType.ALL)
    private Collection<Films> films;
    public Collection<Films> getFilms() {
        return films;
    }
    public void setFilms(Collection<Films> films) {
        this.films = films;
    }
    /**/





    public Age(String value) {
        this.value = value;
    }//конструктор

    public Age() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



}
