package com.example.metacritic.Models.Games;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class DeveloperCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotEmpty(message = "Поле НАЗВАНИЕ КОМПАНИИ не может быть пустым")
    @Size(min=3,max=25,message = "Значение поля должно быть в диапазоне от 3 до 25 символов")
    private String devName;



    /*Связь один ко многим с таблицей игр*/
    @OneToMany(mappedBy = "developerCompany",cascade = CascadeType.ALL)
    private Collection<Games> games;
    public Collection<Games> getGames() {
        return games;
    }
    public void setGames(Collection<Games> games) {
        this.games = games;
    }
    /**/



    public DeveloperCompany() {
    }
    public DeveloperCompany(String devName) {
        this.devName = devName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }
}
