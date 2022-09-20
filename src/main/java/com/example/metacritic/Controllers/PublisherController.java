package com.example.metacritic.Controllers;


import com.example.metacritic.Models.Games.GameGenre;
import com.example.metacritic.Models.Games.Publisher;
import com.example.metacritic.repo.Games.GameGenreRepository;
import com.example.metacritic.repo.Games.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.PeriodUnit;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class PublisherController {

    @Autowired
    public PublisherRepository publisherRepository;

    /*@GetMapping("/games/publisher")
    public String publisherMain(Model model)//главная страницат издателей
    {
        Iterable<Publisher> publisher = publisherRepository.findAll();
        model.addAttribute("publisher",publisher);
        return "games/publisher/publisher-page";
    }*/

    @GetMapping("/games/publisher/add")
    public String publisherAdd(Publisher publisher, Model model)//добавление издателя
    {
        return "games/publisher/publisher-add";
    }//


    @PostMapping("/games/publisher/add")// добавление издвтеля
    public String publisherAdding(
            @ModelAttribute("publisher") @Valid Publisher publisher,//
            BindingResult bindingResult
    )
    {

        List<Publisher> listPublisher = publisherRepository.findByPublisherName(publisher.getPublisherName());//проверка на уникальность имени
        if(bindingResult.hasErrors() || listPublisher.size() > 0)
        {
            return "games/publisher/publisher-add";
        }
        publisherRepository.save(publisher);
        return "redirect:/games/publisher";
    }

    /*@GetMapping("/games/publisher/{id_publisher}")//детали издателя
    public String publisherDetails(
            @PathVariable(value="id_publisher") long id_publisher,
            Model model
    )
    {
        Publisher publisher = publisherRepository.findById(id_publisher);
        model.addAttribute("publisher",publisher);
        return "games/publisher/publisher-details";
    }*/
    @GetMapping("/games/publisher/{id_publisher}/edit")//обновление издателя ГЕТ
    public String genreEdit(
            @ModelAttribute("publisher") Publisher publisher,
            @PathVariable("id_publisher") long id_publisher,
            Model model
    )
    {
        if(!publisherRepository.existsById(id_publisher))
        {return "redirect:/games/publisher";}
        Publisher publisher1 = publisherRepository.findById(id_publisher);
        model.addAttribute("publisher",publisher1);
        return "games/publisher/publisher-edit";
    }


    @PostMapping("/games/publisher/{id_publisher}/edit")//изменение жанра ПОСТ
    public String publisherEditing(
            @ModelAttribute("publisher") @Valid Publisher publisher,
            BindingResult bindingResult,
            @PathVariable("id_publisher") long id_publisher,
            Model model
    )
    {
        List<Publisher> listPublisher = publisherRepository.findByPublisherName(publisher.getPublisherName());//проверка на уникальность имени
        if(bindingResult.hasErrors() || listPublisher.size()>0)
        {
            if(!publisherRepository.existsById(id_publisher))
            {
                return "redirect:/games/publisher";
            }
            Publisher publisher1 = publisherRepository.findById(id_publisher);
            model.addAttribute("publisher",publisher1);
            return "games/publisher/publisher-edit";
        }

        long i = id_publisher;
        Publisher publisher1 = publisherRepository.findById(i);

        publisher1.setPublisherName(publisher.getPublisherName());

        publisherRepository.save(publisher1);
        return "redirect:/games/publisher";
    }


    @PostMapping("/games/publisher/{id_publisher}/delete")//удаление разработчиков
    public String publisherDelete(
            @PathVariable("id_publisher") long id_publisher,
            Model model
    )
    {
        publisherRepository.deleteById(id_publisher);
        return "redirect:/games/publisher";
    }


   /* @GetMapping("/games/publisher/search")
    public String genreSearch(Model model)
    {
        return "games/publisher/publisher-search";
    }

    @PostMapping("/games/publisher/search/result")
    public String genreSearched(
            @RequestParam String publisherName,
            Model model
    )
    {
        List<Publisher> result = publisherRepository.findByPublisherNameContains(publisherName);
        model.addAttribute("result", result);
        return "games/publisher/publisher-search";
    }*/
}
