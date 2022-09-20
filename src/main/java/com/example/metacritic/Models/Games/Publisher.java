package com.example.metacritic.Models.Games;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    @NotEmpty(message = "Поле ИМЯ ИЗДАТЕЛЯ не может быть пустым")
    private String publisherName;

    public Publisher() {
    }
    public Publisher(String publisherName) {
        this.publisherName = publisherName;
    }

    /*Один ко многим*/
    @OneToMany(mappedBy = "publisher",cascade = CascadeType.ALL)//один ко многим
    private Collection<Games> games;

    public Collection<Games> getGames() {
        return games;
    }
    public void setGames(Collection<Games> games) {
        this.games = games;
    }
    /*Один ко многим*/


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPublisherName() {
        return publisherName;
    }
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}
