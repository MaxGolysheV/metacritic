package com.example.metacritic.Controllers;


import com.example.metacritic.Models.Age;
import com.example.metacritic.Models.Films.FilmGenre;
import com.example.metacritic.Models.Films.FilmStudios;
import com.example.metacritic.Models.Films.Films;
import com.example.metacritic.Models.Games.GameGenre;
import com.example.metacritic.repo.AgeRepository;
import com.example.metacritic.repo.Films.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Scanner;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class FilmsController {


   @Autowired
   public FilmRepository filmRepository;
   @Autowired
   public AgeRepository ageRepository;

   @Autowired
   public FilmStudiosRepository filmStudiosRepository;

   @Autowired
   public FilmGenreRepository filmGenreRepository;



    /*@GetMapping("/films")
    public String filmsMain(Model model)//главная страницца жанров игр
    {
        Iterable<Films> films = filmRepository.findAll();
        model.addAttribute("films",films);
        return "films/film-page";
    }*/

    @GetMapping("/films/add")
    public String filmsAdd(Films films, Model model)
    {
        Iterable<Age> age = ageRepository.findAll();//
        model.addAttribute("age",age);

        Iterable<FilmStudios> filmStudio = filmStudiosRepository.findAll();//
        model.addAttribute("filmStudio",filmStudio);

        Iterable<FilmGenre> filmGenre = filmGenreRepository.findAll();//
        model.addAttribute("filmGenre",filmGenre);

        return "films/film-add";
    }


    @PostMapping("/films/add")// добавление жанра
    public String filmsAdding(
            @ModelAttribute("films") @Valid Films films, BindingResult bindingResult,
            @RequestParam Age age,
            @RequestParam FilmStudios filmStudio,
            @RequestParam FilmGenre filmGenre,
            Model model

    )
    {
        Iterable<Age> age1 = ageRepository.findAll();//
        model.addAttribute("age",age1);

        Iterable<FilmStudios> filmStudios1 = filmStudiosRepository.findAll();//
        model.addAttribute("filmStudio",filmStudios1);

        Iterable<FilmGenre> filmGenre1 = filmGenreRepository.findAll();//
        model.addAttribute("filmGenre",filmGenre1);


        List<Films> listFilms = filmRepository.findByFilmName(films.getFilmName());//проверка на уникальность имени
        if(bindingResult.hasErrors() || listFilms.size() > 0)
        {
            return "films/film-add";
        }


        films.setFilmGenre(filmGenre);
        films.setFilmStudio(filmStudio);
        films.setAge(age);

        filmRepository.save(films);
        return "redirect:/films";
    }

    /*@GetMapping("/films/{id_film}")
    public String filmDetails(@PathVariable(value="id_film") long id_film, Model model)
    {
        Films films = filmRepository.findById(id_film);
        model.addAttribute("films", films);
        return "films/film-details";
    }*/

    @GetMapping("/films/{id_film}/edit")
    public String filmsEdit(
            @ModelAttribute("films") Films films,

            @PathVariable("id_film") long id_film,
            Model model )
    {
        if(!filmRepository.existsById(id_film))
        {return "redirect:/films";}

        Iterable<Age> age = ageRepository.findAll();//
        model.addAttribute("age",age);

        Iterable<FilmStudios> filmStudio = filmStudiosRepository.findAll();//
        model.addAttribute("filmStudio",filmStudio);

        Iterable<FilmGenre> filmGenre = filmGenreRepository.findAll();//
        model.addAttribute("filmGenre",filmGenre);

        long i = id_film;
        Films films1 = filmRepository.findById(i);
        model.addAttribute("films",films1);

        return "films/film-edit";
    }

    @PostMapping("/films/{id_film}/edit")
    public String weaponsUpdate(
            @ModelAttribute("films") @Valid Films films,
            BindingResult bindingResult,
            @RequestParam Age age,
            @RequestParam FilmStudios filmStudio,
            @RequestParam FilmGenre filmGenre,
            @PathVariable("id_film")long id_film
            ,Model model)
    {

        Iterable<Age> age1 = ageRepository.findAll();//
        model.addAttribute("age",age1);

        Iterable<FilmStudios> filmStudio1 = filmStudiosRepository.findAll();//
        model.addAttribute("filmStudio",filmStudio1);

        Iterable<FilmGenre> filmGenre1 = filmGenreRepository.findAll();//
        model.addAttribute("filmGenre",filmGenre1);
        if(bindingResult.hasErrors())
        {
            if(!filmRepository.existsById(id_film))
            {return "redirect:/films";}
            long i = id_film;
            Films films1 = filmRepository.findById(i);
            model.addAttribute("films",films1);
            return "films/film-edit";
        }

        long i = id_film;
        Films films2 = filmRepository.findById(i);

        films2.setFilmName(films.getFilmName());
        films2.setFilmGenre(filmGenre);
        films2.setFilmStudio(filmStudio);
        films2.setAge(age);

        filmRepository.save(films2);
        return "redirect:/films";
    }


    @PostMapping("/films/{id_film}/delete")//удаление разработчиков
    public String filmsDelete(
            @PathVariable("id_film") Long id_film,
            Model model
    )
    {
        filmRepository.deleteById(id_film);
        return "redirect:/films";
    }


   /* @GetMapping("/films/search")
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
    }*/

}
