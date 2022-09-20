package com.example.metacritic.Controllers;

import com.example.metacritic.Models.Films.FilmStudios;
import com.example.metacritic.Models.Films.Films;
import com.example.metacritic.Models.Games.GameGenre;
import com.example.metacritic.repo.Films.FilmStudiosRepository;
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
public class StudiosController {

    @Autowired
    public FilmStudiosRepository filmStudiosRepository;

    /*@GetMapping("/films/studios")
    public String genreMain(Model model)//главная страницца студий
    {
        Iterable<FilmStudios> filmStudios = filmStudiosRepository.findAll();
        model.addAttribute("filmStudios",filmStudios);
        return "films/studios/studios-page";
    }*/

    @GetMapping("/films/studios/add")
    public String genreAdd(FilmStudios filmStudios, Model model)
    {
        return "films/studios/studios-add";
    }//


    @PostMapping("/films/studios/add")// добавление студии
    public String genreAdding(
            @ModelAttribute("filmStudios") @Valid FilmStudios filmStudios,//
            BindingResult bindingResult
    )
    {

        List<FilmStudios> listStudios = filmStudiosRepository.findByFilmStudioName(filmStudios.getFilmStudioName());//проверка на уникальность имени
        if(bindingResult.hasErrors() || listStudios.size() > 0)
        {
            return "films/studios/studios-add";
        }
        filmStudiosRepository.save(filmStudios);
        return "redirect:/films/studios";
    }


    /*@GetMapping("/films/studios/{id_studio}")//детали студии
    public String genreDetails(
            @PathVariable(value="id_studio") long id_studio,
            Model model
    )
    {
        FilmStudios filmStudios = filmStudiosRepository.findById(id_studio);
        model.addAttribute("filmStudios",filmStudios);
        return "films/studios/studios-details";
    }*/

    @GetMapping("/films/studios/{id_studio}/edit")//обновление студии ГЕТ
    public String genreEdit(
            @ModelAttribute("filmStudios") FilmStudios filmStudios,
            @PathVariable("id_studio") long id_studio,
            Model model
    )
    {
        if(!filmStudiosRepository.existsById(id_studio))
        {return "redirect:/films/studios";}

        FilmStudios filmStudios1 =filmStudiosRepository.findById(id_studio);
        model.addAttribute("filmStudios",filmStudios1);
        return "films/studios/studios-edit";
    }


    @PostMapping("/films/studios/{id_studio}/edit")//изменение жанра ПОСТ
    public String genreEditing(
            @ModelAttribute("filmStudios") @Valid FilmStudios filmStudios,
            BindingResult bindingResult,
            @PathVariable("id_studio") long id_studio,
            Model model
    )
    {
        List<FilmStudios> listStudios = filmStudiosRepository.findByFilmStudioName(filmStudios.getFilmStudioName());//проверка на уникальность имени
        if(bindingResult.hasErrors() || listStudios.size()>0)
        {
            if(!filmStudiosRepository.existsById(id_studio))
            {
                return "redirect:/films/studios";
            }
            FilmStudios filmStudios1 = filmStudiosRepository.findById(id_studio);
            model.addAttribute("filmStudios",filmStudios1);
            return "films/studios/studios-edit";
        }

        long i = id_studio;
        FilmStudios filmStudios1 = filmStudiosRepository.findById(i);

        filmStudios1.setFilmStudioName(filmStudios.getFilmStudioName());

        filmStudiosRepository.save(filmStudios1);
        return "redirect:/films/studios";
    }


    @PostMapping("/films/studios/{id_studio}/delete")//удаление студии
    public String developerDelete(
            @PathVariable("id_studio") Long id_studio,
            Model model
    )
    {
        filmStudiosRepository.deleteById(id_studio);
        return "redirect:/films/studios";
    }


  /*  @GetMapping("/films/studios/search")
    public String genreSearch(Model model)
    {
        return "films/studios/studios-search";
    }

    @PostMapping("/films/studios/search/result")
    public String genreSearched(
            @RequestParam String studName,
            Model model
    )
    {
        List<FilmStudios> result = filmStudiosRepository.findByFilmStudioNameContains(studName);
        model.addAttribute("result", result);
        return "films/studios/studios-search";
    }*/
}
