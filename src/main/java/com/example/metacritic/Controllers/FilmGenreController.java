package com.example.metacritic.Controllers;


import com.example.metacritic.Models.Films.FilmGenre;
import com.example.metacritic.Models.Games.DeveloperCompany;
import com.example.metacritic.Models.Games.GameGenre;
import com.example.metacritic.repo.Films.FilmGenreRepository;
import com.example.metacritic.repo.Games.GameGenreRepository;
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
public class FilmGenreController {

    @Autowired
    public FilmGenreRepository filmGenreRepository;

    /*@GetMapping("/films/genre")
    public String filmGenreMain(Model model)//главная страницца жанров фильмов
    {
        Iterable<FilmGenre> filmGenre = filmGenreRepository.findAll();
        model.addAttribute("filmGenre",filmGenre);
        return "films/filmGenre/filmGenre-page";
    }*/

    @GetMapping("/films/genre/add")
    public String filmGenreAdd(FilmGenre filmGenre, Model model)
    {
        return "films/filmGenre/filmGenre-add";
    }

    @PostMapping("/films/genre/add")// добавление жанра
    public String filmGenreAdding(
            @ModelAttribute("filmGenre") @Valid FilmGenre filmGenre,//
            BindingResult bindingResult
    )
    {
        List<FilmGenre> listGenre = filmGenreRepository.findByGenreName(filmGenre.getGenreName());//проверка на уникальность имени
        if(bindingResult.hasErrors() || listGenre.size() > 0)
        {
            return "films/filmGenre/filmGenre-add";
        }
        filmGenreRepository.save(filmGenre);
        return "redirect:/films/genre";
    }

    /*@GetMapping("/films/genre/{id_genre}")//детали жанра
    public String filmGenreDetails(
            @PathVariable(value="id_genre") long id_genre,
            Model model
    )
    {
        FilmGenre filmGenre = filmGenreRepository.findById(id_genre);
        model.addAttribute("filmGenre",filmGenre);
        return "films/filmGenre/filmGenre-details";
    }*/

    @GetMapping("/films/genre/{id_genre}/edit")//обновление жанра ГЕТ
    public String filmGenreEdit(
            @ModelAttribute("filmGenre") FilmGenre filmGenre,
            @PathVariable("id_genre") long id_genre,
            Model model
    )
    {
        if(!filmGenreRepository.existsById(id_genre))
        {return "redirect:/films/genre";}

        FilmGenre filmGenre1 =filmGenreRepository.findById(id_genre);
        model.addAttribute("filmGenre",filmGenre1);
        return "films/filmGenre/filmGenre-edit";
    }


    @PostMapping("/films/genre/{id_genre}/edit")//изменение жанра ПОСТ
    public String filmGenreEditing(
            @ModelAttribute("filmGenre") @Valid FilmGenre filmGenre,
            BindingResult bindingResult,
            @PathVariable("id_genre") long id_genre,
            Model model
    )
    {
        List<FilmGenre> listGenre = filmGenreRepository.findByGenreName(filmGenre.getGenreName());//проверка на уникальность имени
        if(bindingResult.hasErrors() || listGenre.size()>0)
        {
            if(!filmGenreRepository.existsById(id_genre))
            {
                return "redirect:/films/genre";
            }
            FilmGenre filmGenre1 = filmGenreRepository.findById(id_genre);
            model.addAttribute("filmGenre",filmGenre1);
            return "films/filmGenre/filmGenre-edit";
        }

        long i = id_genre;
        FilmGenre filmGenre1 = filmGenreRepository.findById(i);

        filmGenre1.setGenreName(filmGenre.getGenreName());

        filmGenreRepository.save(filmGenre1);
        return "redirect:/films/genre";
    }

    @PostMapping("/films/genre/{id_genre}/delete")//удаление разработчиков
    public String filmGenreDelete(
            @PathVariable("id_genre") Long id_genre,
            Model model
    )
    {
        filmGenreRepository.deleteById(id_genre);
        return "redirect:/films/genre";
    }


    /*@GetMapping("/films/genre/search")
    public String devComSearch(Model model)
    {
        return "films/filmGenre/filmGenre-search";
    }

    @PostMapping("/films/genre/search/result")
    public String devComSearched(
            @RequestParam String genreName,
            Model model
    )
    {
        List<FilmGenre> result = filmGenreRepository.findByGenreNameContains(genreName);
        model.addAttribute("result", result);
        return "films/filmGenre/filmGenre-search";
    }*/


}
