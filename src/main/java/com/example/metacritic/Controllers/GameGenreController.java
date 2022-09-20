package com.example.metacritic.Controllers;

import com.example.metacritic.Models.Films.Films;
import com.example.metacritic.Models.Games.GameGenre;
import com.example.metacritic.Models.Games.Games;
import com.example.metacritic.repo.Games.GameGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import javax.validation.Valid;
import java.util.List;


@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class GameGenreController {


    @Autowired
    public GameGenreRepository gameGenreRepository;

    /*@GetMapping("/games/genre")
    public String genreMain(Model model)//главная страницца жанров игр
    {
        Iterable<GameGenre> gameGenres = gameGenreRepository.findAll();
        model.addAttribute("gameGenre",gameGenres);
        return "games/gameGenre/gamegenre-page";
    }*/

    @GetMapping("/games/genre/add")
    public String genreAdd(GameGenre gameGenre, Model model)
    {
        return "games/gameGenre/gamegenre-add";
    }//

    @PostMapping("/games/genre/add")// добавление жанра
    public String genreAdding(
            @ModelAttribute("gameGenre") @Valid GameGenre gameGenre,//
            BindingResult bindingResult
    )
    {

        List<GameGenre> listGenre = gameGenreRepository.findByGenreName(gameGenre.getGenreName());//проверка на уникальность имени
        if(bindingResult.hasErrors() || listGenre.size() > 0)
        {
            return "games/gameGenre/gamegenre-add";
        }
        gameGenreRepository.save(gameGenre);
        return "redirect:/games/genre";
    }


   /* @GetMapping("/games/genre/{id_genre}")//детали жанра
    public String genreDetails(
            @PathVariable(value="id_genre") long id_genre,
            Model model
    )
    {
        GameGenre gameGenre = gameGenreRepository.findById(id_genre);
        model.addAttribute("gameGenre",gameGenre);
        return "games/gameGenre/gamegenre-details";
    }*/

    @GetMapping("/games/genre/{id_genre}/edit")//обновление жанра ГЕТ
    public String genreEdit(
            @ModelAttribute("gameGenre") GameGenre gameGenre,
            @PathVariable("id_genre") long id_genre,
            Model model
    )
    {
        if(!gameGenreRepository.existsById(id_genre))
        {return "redirect:/games/genre";}

        GameGenre gameGenre1 =gameGenreRepository.findById(id_genre);
        model.addAttribute("gameGenre",gameGenre1);
        return "games/gameGenre/gamegenre-edit";
    }


    @PostMapping("/games/genre/{id_genre}/edit")//изменение жанра ПОСТ
    public String genreEditing(
            @ModelAttribute("gameGenre") @Valid GameGenre gameGenre,
            BindingResult bindingResult,
            @PathVariable("id_genre") long id_genre,
            Model model
    )
    {
        List<GameGenre> listGenre = gameGenreRepository.findByGenreName(gameGenre.getGenreName());//проверка на уникальность имени
        if(bindingResult.hasErrors() || listGenre.size()>0)
        {
            if(!gameGenreRepository.existsById(id_genre))
            {
                return "redirect:/games/genre";
            }
            GameGenre gameGenre1 = gameGenreRepository.findById(id_genre);
            model.addAttribute("gameGenre",gameGenre1);
            return "games/gameGenre/gamegenre-edit";
        }

        long i = id_genre;
        GameGenre gameGenre1 = gameGenreRepository.findById(i);

        gameGenre1.setGenreName(gameGenre.getGenreName());

        gameGenreRepository.save(gameGenre1);
        return "redirect:/games/genre";
    }


    @PostMapping("/games/genre/{id_genre}/delete")//удаление разработчиков
    public String developerDelete(
            @PathVariable("id_genre") Long id_genre,
            Model model
    )
    {
        gameGenreRepository.deleteById(id_genre);
        return "redirect:/games/genre";
    }



   /* @GetMapping("/games/genre/search")
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
    }*/
}
