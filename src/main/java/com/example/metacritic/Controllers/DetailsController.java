package com.example.metacritic.Controllers;


import com.example.metacritic.Models.Films.FilmGenre;
import com.example.metacritic.Models.Films.FilmStudios;
import com.example.metacritic.Models.Films.Films;
import com.example.metacritic.Models.Games.DeveloperCompany;
import com.example.metacritic.Models.Games.GameGenre;
import com.example.metacritic.Models.Games.Games;
import com.example.metacritic.Models.Games.Publisher;
import com.example.metacritic.repo.Films.FilmGenreRepository;
import com.example.metacritic.repo.Films.FilmRepository;
import com.example.metacritic.repo.Films.FilmStudiosRepository;
import com.example.metacritic.repo.Games.DeveloperCompanyRepository;
import com.example.metacritic.repo.Games.GameGenreRepository;
import com.example.metacritic.repo.Games.GamesRepository;
import com.example.metacritic.repo.Games.PublisherRepository;
import net.bytebuddy.utility.nullability.AlwaysNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DetailsController {

    @Autowired
    public DeveloperCompanyRepository developerCompanyRepository;

    @GetMapping("/games/developers/{id_dev}")//детали разработчиков
    public String developersDetails(
            @PathVariable(value = "id_dev") long id_dev, Model model
    )
    {
        DeveloperCompany developerCompany = developerCompanyRepository.findById(id_dev);
        model.addAttribute("developerCompany",developerCompany);
        return "/games/developersCompany/devcom-details";
    }


    @Autowired
    public FilmGenreRepository filmGenreRepository;
    @GetMapping("/films/genre/{id_genre}")//детали жанра
    public String filmGenreDetails(
            @PathVariable(value="id_genre") long id_genre,
            Model model
    )
    {
        FilmGenre filmGenre = filmGenreRepository.findById(id_genre);
        model.addAttribute("filmGenre",filmGenre);
        return "films/filmGenre/filmGenre-details";
    }


    @Autowired
    public FilmRepository filmRepository;
    @GetMapping("/films/{id_film}")
    public String filmDetails(@PathVariable(value="id_film") long id_film, Model model)
    {
        Films films = filmRepository.findById(id_film);
        model.addAttribute("films", films);
        return "films/film-details";
    }

    @Autowired
    public GameGenreRepository gameGenreRepository;
    @GetMapping("/games/genre/{id_genre}")//детали жанра
    public String genreDetails(
            @PathVariable(value="id_genre") long id_genre,
            Model model
    )
    {
        GameGenre gameGenre = gameGenreRepository.findById(id_genre);
        model.addAttribute("gameGenre",gameGenre);
        return "games/gameGenre/gamegenre-details";
    }

    @Autowired
    public GamesRepository gamesRepository;
    @GetMapping("/games/{id_games}")
    public String gameDetails(@PathVariable(value="id_games") long id_games, Model model)
    {
        Games games = gamesRepository.findById(id_games);
        model.addAttribute("games", games);
        return "games/games-details";
    }


    @Autowired
    public PublisherRepository publisherRepository;
    @GetMapping("/games/publisher/{id_publisher}")//детали издателя
    public String publisherDetails(
            @PathVariable(value="id_publisher") long id_publisher,
            Model model
    )
    {
        Publisher publisher = publisherRepository.findById(id_publisher);
        model.addAttribute("publisher",publisher);
        return "games/publisher/publisher-details";
    }

    @Autowired
    public FilmStudiosRepository filmStudiosRepository;
    @GetMapping("/films/studios/{id_studio}")//детали студии
    public String studiosDetails(
            @PathVariable(value="id_studio") long id_studio,
            Model model
    )
    {
        FilmStudios filmStudios = filmStudiosRepository.findById(id_studio);
        model.addAttribute("filmStudios",filmStudios);
        return "films/studios/studios-details";
    }
}
