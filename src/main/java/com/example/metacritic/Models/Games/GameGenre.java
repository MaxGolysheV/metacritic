package com.example.metacritic.Models.Games;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class GameGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    @NotEmpty(message = "Поле ЖАНРА не может быть пустым")
    @Size(min=3,max=25,message = "Значение поле должно быть в диапазоне от 3 до 25 символов")
    private String genreName;



    /*Связь один ко многим с таблицей игр*/
    @OneToMany(mappedBy = "gameGenre",  cascade = CascadeType.ALL)
    private Collection<Games> games;
    public Collection<Games> getGames() {
        return games;
    }
    public void setGames(Collection<Games> games) {
        this.games = games;
    }
    /**/

    public GameGenre(String genreName) {
        this.genreName = genreName;
    }

    public GameGenre() {
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

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
