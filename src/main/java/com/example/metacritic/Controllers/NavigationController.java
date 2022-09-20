package com.example.metacritic.Controllers;

import com.example.metacritic.Models.Films.FilmGenre;
import com.example.metacritic.Models.Films.FilmStudios;
import com.example.metacritic.Models.Films.Films;
import com.example.metacritic.Models.Games.DeveloperCompany;
import com.example.metacritic.Models.Games.GameGenre;
import com.example.metacritic.Models.Games.Games;
import com.example.metacritic.Models.Games.Publisher;
import com.example.metacritic.repo.AgeRepository;
import com.example.metacritic.repo.Films.FilmGenreRepository;
import com.example.metacritic.repo.Films.FilmRepository;
import com.example.metacritic.repo.Films.FilmStudiosRepository;
import com.example.metacritic.repo.Games.DeveloperCompanyRepository;
import com.example.metacritic.repo.Games.GameGenreRepository;
import com.example.metacritic.repo.Games.GamesRepository;
import com.example.metacritic.repo.Games.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {


    @Autowired
    DeveloperCompanyRepository developerCompanyRepository;

    @GetMapping("/games/developers")//просмотр
    public String developersMain(Model model)
    {
        Iterable<DeveloperCompany> developerCompany = developerCompanyRepository.findAll();
        model.addAttribute("developerCompany", developerCompany);//
        return "games/developersCompany/devcom-page";
    }

    @Autowired
    public FilmGenreRepository filmGenreRepository;

    @GetMapping("/films/genre")
    public String filmGenreMain(Model model)//главная страницца жанров фильмов
    {
        Iterable<FilmGenre> filmGenre = filmGenreRepository.findAll();
        model.addAttribute("filmGenre",filmGenre);
        return "films/filmGenre/filmGenre-page";
    }

    @Autowired
    public FilmRepository filmRepository;

    @GetMapping("/films")
    public String filmsMain(Model model)//главная страницца жанров игр
    {
        Iterable<Films> films = filmRepository.findAll();
        model.addAttribute("films",films);
        return "films/film-page";
    }



    @Autowired
    public GameGenreRepository gameGenreRepository;

    @GetMapping("/games/genre")
    public String genreMain(Model model)//главная страницца жанров игр
    {
        Iterable<GameGenre> gameGenres = gameGenreRepository.findAll();
        model.addAttribute("gameGenre",gameGenres);
        return "games/gameGenre/gamegenre-page";
    }

    @Autowired
    public GamesRepository gamesRepository;

    @GetMapping("/")
    public String Main(Model model)//главная страницца
    {
        Iterable<Games> games = gamesRepository.findAll();
        model.addAttribute("games",games);
        return "games/games-page";
    }

    @GetMapping("/games")
    public String gamesMain(Model model)//главная страницца
    {
        Iterable<Games> games = gamesRepository.findAll();
        model.addAttribute("games",games);
        return "games/games-page";
    }

    @Autowired
    public PublisherRepository publisherRepository;

    @GetMapping("/games/publisher")
    public String publisherMain(Model model)//главная страницат издателей
    {
        Iterable<Publisher> publisher = publisherRepository.findAll();
        model.addAttribute("publisher",publisher);
        return "games/publisher/publisher-page";
    }


    @Autowired
    public FilmStudiosRepository filmStudiosRepository;

    @GetMapping("/films/studios")
    public String studiosMain(Model model)//главная страницца студий
    {
        Iterable<FilmStudios> filmStudios = filmStudiosRepository.findAll();
        model.addAttribute("filmStudios",filmStudios);
        return "films/studios/studios-page";
    }
}
