package com.example.metacritic.Controllers;

import com.example.metacritic.Models.Age;
import com.example.metacritic.Models.Films.FilmGenre;
import com.example.metacritic.Models.Films.FilmStudios;
import com.example.metacritic.Models.Films.Films;
import com.example.metacritic.Models.Games.DeveloperCompany;
import com.example.metacritic.Models.Games.GameGenre;
import com.example.metacritic.Models.Games.Games;
import com.example.metacritic.Models.Games.Publisher;
import com.example.metacritic.repo.AgeRepository;
import com.example.metacritic.repo.Games.DeveloperCompanyRepository;
import com.example.metacritic.repo.Games.GameGenreRepository;
import com.example.metacritic.repo.Games.GamesRepository;
import com.example.metacritic.repo.Games.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class GamesController {


    @Autowired
    public GamesRepository gamesRepository;
    @Autowired
    public AgeRepository ageRepository;
    @Autowired
    public GameGenreRepository gameGenreRepository;
    @Autowired
    public PublisherRepository publisherRepository;
    @Autowired
    public DeveloperCompanyRepository developerCompanyRepository;

    /*@GetMapping("/")
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
    }*/

    @GetMapping("/games/add")
    public String gamesAdd(Games games, Model model)
    {
        Iterable<Age> age = ageRepository.findAll();//
        model.addAttribute("age",age);

        Iterable<DeveloperCompany> developerCompany = developerCompanyRepository.findAll();//
        model.addAttribute("developerCompany",developerCompany);

        Iterable<GameGenre> gameGenre = gameGenreRepository.findAll();//
        model.addAttribute("gameGenre",gameGenre);

        Iterable<Publisher> publisher = publisherRepository.findAll();//
        model.addAttribute("publisher",publisher);

        return "games/games-add";
    }


    @PostMapping("/games/add")// добавление игры
    public String gamesAdding(
            @ModelAttribute("games") @Valid Games games,BindingResult bindingResult,
            @RequestParam Age age,
            @RequestParam DeveloperCompany developerCompany,
            @RequestParam GameGenre gameGenre,
            @RequestParam Publisher publisher,
            Model model

    )
    {

        Iterable<Age> age1 = ageRepository.findAll();//
        model.addAttribute("age",age1);

        Iterable<DeveloperCompany> developerCompany1 = developerCompanyRepository.findAll();//
        model.addAttribute("developerCompany",developerCompany1);

        Iterable<GameGenre> gameGenre1 = gameGenreRepository.findAll();//
        model.addAttribute("gameGenre",gameGenre1);

        Iterable<Publisher> publisher1 = publisherRepository.findAll();//
        model.addAttribute("publisher",publisher1);

        List<Games> listGames = gamesRepository.findByGameName(games.getGameName());//проверка на уникальность имени
        if(bindingResult.hasErrors() || listGames.size() > 0)
        {
            return "games/games-add";
        }

        games.setGameGenre(gameGenre);
        games.setPublisher(publisher);
        games.setDeveloperCompany(developerCompany);
        games.setAge(age);

        gamesRepository.save(games);
        return "redirect:/games";
    }

   /* @GetMapping("/games/{id_games}")
    public String gameDetails(@PathVariable(value="id_games") long id_games, Model model)
    {
        Games games = gamesRepository.findById(id_games);
        model.addAttribute("games", games);
        return "games/games-details";
    }*/


    @GetMapping("/games/{id_games}/edit")
    public String gamesEdit(
            @ModelAttribute("games") Games games,

            @PathVariable("id_games") long id_games,
            Model model )
    {
        if(!gamesRepository.existsById(id_games))
        {return "redirect:/games";}

        Iterable<Age> age = ageRepository.findAll();//
        model.addAttribute("age",age);

        Iterable<DeveloperCompany> developerCompany = developerCompanyRepository.findAll();//
        model.addAttribute("developerCompany",developerCompany);

        Iterable<GameGenre> gameGenre = gameGenreRepository.findAll();//
        model.addAttribute("gameGenre",gameGenre);

        Iterable<Publisher> publisher = publisherRepository.findAll();//
        model.addAttribute("publisher",publisher);

        long i = id_games;
        Games games1 = gamesRepository.findById(i);
        model.addAttribute("games",games1);

        return "games/games-edit";
    }


    @PostMapping("/games/{id_games}/edit")
    public String gamesUpdate(
            @ModelAttribute("games") @Valid Games games,
            BindingResult bindingResult,
            @RequestParam Age age,
            @RequestParam DeveloperCompany developerCompany,
            @RequestParam GameGenre gameGenre,
            @RequestParam Publisher publisher,
            @PathVariable("id_games")long id_games
            ,Model model)
    {

        Iterable<Age> age1 = ageRepository.findAll();//
        model.addAttribute("age",age1);

        Iterable<DeveloperCompany> developerCompany1 = developerCompanyRepository.findAll();//
        model.addAttribute("developerCompany",developerCompany1);

        Iterable<GameGenre> gameGenre1 = gameGenreRepository.findAll();//
        model.addAttribute("gameGenre",gameGenre1);

        Iterable<Publisher> publisher1 = publisherRepository.findAll();//
        model.addAttribute("publisher",publisher1);



        if(bindingResult.hasErrors())
        {
            if(!gamesRepository.existsById(id_games))
            {return "redirect:/games";}
            long i = id_games;
            Games games1 = gamesRepository.findById(i);
            model.addAttribute("games",games1);
            return "games/games-edit";
        }

        long i = id_games;
        Games games2 = gamesRepository.findById(i);

        games2.setGameName(games.getGameName());
        games2.setInfo(games.getInfo());
        games2.setPrice(games.getPrice());
        games2.setGameGenre(gameGenre);
        games2.setPublisher(publisher);
        games2.setDeveloperCompany(developerCompany);
        games2.setAge(age);

        gamesRepository.save(games2);
        return "redirect:/games";
    }

    @PostMapping("/games/{id_games}/delete")//удаление разработчиков
    public String gamesDelete(
            @PathVariable("id_games") Long id_games,
            Model model
    )
    {
        gamesRepository.deleteById(id_games);
        return "redirect:/games";
    }

    /*@GetMapping("/games/search")
    public String genreSearch(Model model)
    {
        return "games/games-search";
    }

    @PostMapping("/games/search/result")
    public String genreSearched(
            @RequestParam String gameName,
            Model model
    )
    {
        List<Games> result = gamesRepository.findByGameNameContains(gameName);
        model.addAttribute("result", result);
        return "games/games-search";
    }*/
}
