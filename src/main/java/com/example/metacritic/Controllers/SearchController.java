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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {


    @Autowired
    public DeveloperCompanyRepository developerCompanyRepository;
    @GetMapping("/games/developers/search")
    public String devComSearch(Model model)
    {
        return "games/developersCompany/devcom-search";
    }

    @PostMapping("/games/developers/search/result")
    public String devComSearched(
            @RequestParam String devName,
            Model model
    )
    {
        List<DeveloperCompany> result = developerCompanyRepository.findByDevNameContains(devName);
        model.addAttribute("result", result);
        return "games/developersCompany/devcom-search";
    }


    @Autowired
    public FilmGenreRepository filmGenreRepository;
    @GetMapping("/films/genre/search")
    public String filmGenreSearch(Model model)
    {
        return "films/filmGenre/filmGenre-search";
    }

    @PostMapping("/films/genre/search/result")
    public String filmGenreSearched(
            @RequestParam String genreName,
            Model model
    )
    {
        List<FilmGenre> result = filmGenreRepository.findByGenreNameContains(genreName);
        model.addAttribute("result", result);
        return "films/filmGenre/filmGenre-search";
    }


    @Autowired
    public FilmRepository filmRepository;
    @GetMapping("/films/search")
    public String filmsSearch(Model model)
    {
        return "films/film-search";
    }

    @PostMapping("/films/search/result")
    public String filmsSearched(
            @RequestParam String filmName,
            Model model
    )
    {
        List<Films> result = filmRepository.findByFilmNameContains(filmName);
        model.addAttribute("result", result);
        return "films/film-search";
    }


    @Autowired
    public GameGenreRepository gameGenreRepository;
    @GetMapping("/games/genre/search")
    public String genreSearch(Model model)
    {
        return "games/gameGenre/gamegenre-search";
    }

    @PostMapping("/games/genre/search/result")
    public String genreSearched(
            @RequestParam String genreName,
            Model model
    )
    {
        List<GameGenre> result = gameGenreRepository.findByGenreNameContains(genreName);
        model.addAttribute("result", result);
        return "games/gameGenre/gamegenre-search";
    }

    @Autowired
    public GamesRepository gamesRepository;
    @GetMapping("/games/search")
    public String gamesSearch(Model model)
    {
        return "games/games-search";
    }

    @PostMapping("/games/search/result")
    public String gamesSearched(
            @RequestParam String gameName,
            Model model
    )
    {
        List<Games> result = gamesRepository.findByGameNameContains(gameName);
        model.addAttribute("result", result);
        return "games/games-search";
    }

    @Autowired
    public PublisherRepository publisherRepository;
    @GetMapping("/games/publisher/search")
    public String pubSearch(Model model)
    {
        return "games/publisher/publisher-search";
    }

    @PostMapping("/games/publisher/search/result")
    public String pubSearched(
            @RequestParam String publisherName,
            Model model
    )
    {
        List<Publisher> result = publisherRepository.findByPublisherNameContains(publisherName);
        model.addAttribute("result", result);
        return "games/publisher/publisher-search";
    }


    @Autowired
    public FilmStudiosRepository filmStudiosRepository;
    @GetMapping("/films/studios/search")
    public String studSearch(Model model)
    {
        return "films/studios/studios-search";
    }

    @PostMapping("/films/studios/search/result")
    public String studSearched(
            @RequestParam String studName,
            Model model
    )
    {
        List<FilmStudios> result = filmStudiosRepository.findByFilmStudioNameContains(studName);
        model.addAttribute("result", result);
        return "films/studios/studios-search";
    }

}
