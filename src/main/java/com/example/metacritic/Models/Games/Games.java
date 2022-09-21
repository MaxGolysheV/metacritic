package com.example.metacritic.Models.Games;

import com.example.metacritic.Models.Age;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Games {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @NotEmpty(message = "Поле НАЗВАНИЕ ИГРЫ не может быть пустым")
    @Size(min=3,max=25,message = "Значение поле должно быть в диапазоне от 3 до 25 символов")
    private String gameName;

    @NotNull(message = "Поле ЦЕНА ИГРЫ не может быть пустым")
    private double price;

    @NotNull
    @NotEmpty(message = "Поле ИНФОРМАЦИЯ ОБ ИГРЕ не может быть пустым")
    @Size(min=3,max=100,message = "Значение поле должно быть в диапазоне от 3 до 100 символов")
    private String info;


    /*Связь многие к олному с таблицей издателя*/

    @NonNull//
    @ManyToOne(optional = true)
    private Publisher publisher;


    public Publisher getPublisher() {
        return publisher;
    }
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    /**/

    /*Связь многоие к одному с таблицей жанра*/
    @NotNull
    @ManyToOne(optional = true)
    private GameGenre gameGenre;
    public GameGenre getGameGenre() {
        return gameGenre;
    }
    public void setGameGenre(GameGenre gameGenre) {
        this.gameGenre = gameGenre;
    }
    /**/


    /*Связь многоие к одному с таблицей разработчика*/
    @NonNull
    @ManyToOne(optional = true)
    private DeveloperCompany developerCompany;
    public DeveloperCompany getDeveloperCompany() {
        return developerCompany;
    }
    public void setDeveloperCompany(DeveloperCompany developerCompany) {
        this.developerCompany = developerCompany;
    }
    /**/

    /*Связь многие к одному с таблиццей возраста*/

    @NotNull
    @ManyToOne(optional = true)
    private Age age;
    public Age getAge() {
        return age;
    }
    public void setAge(Age age) {
        this.age = age;
    }
    /**/

    public Games(String gameName, double price, String info, @NonNull Publisher publisher, GameGenre gameGenre, @NonNull DeveloperCompany developerCompany, Age age) {
        this.gameName = gameName;
        this.price = price;
        this.info = info;
        this.publisher = publisher;
        this.gameGenre = gameGenre;
        this.developerCompany = developerCompany;
        this.age = age;
    }

    public Games() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
