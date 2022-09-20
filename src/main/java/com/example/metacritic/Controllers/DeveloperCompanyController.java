package com.example.metacritic.Controllers;


import com.example.metacritic.Models.Games.DeveloperCompany;
import com.example.metacritic.Models.Games.GameGenre;
import com.example.metacritic.repo.Games.DeveloperCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class DeveloperCompanyController {

    @Autowired
    DeveloperCompanyRepository developerCompanyRepository;

   /* @GetMapping("/games/developers")//просмотр
    public String developersMain(Model model)
    {
        Iterable<DeveloperCompany> developerCompany = developerCompanyRepository.findAll();
        model.addAttribute("developerCompany", developerCompany);//
        return "games/developersCompany/devcom-page";
    }*/

    @GetMapping("/games/developers/add")
    public  String developersCompanyAdd(DeveloperCompany developerCompany, Model model)//добавление
    {
        return "games/developersCompany/devcom-add";
    }

    @PostMapping("/games/developers/add")//добавление разработчиков
    public String developersCompanyAdding(
            @ModelAttribute("developerCompany") @Valid DeveloperCompany developerCompany,//
            BindingResult bindingResult
    )
    {
        List<DeveloperCompany> listDev = developerCompanyRepository.findByDevName(developerCompany.getDevName());//проверка на уникальность имени

        if(bindingResult.hasErrors() || listDev.size()>0)
        {
            return "games/developersCompany/devcom-add";//
        }
        developerCompanyRepository.save(developerCompany);//
        return "redirect:/games/developers";//
    }

    /*@GetMapping("/games/developers/{id_dev}")//детали разработчиков
    public String developersDetails(
            @PathVariable(value = "id_dev") long id_dev, Model model
    )
    {
        DeveloperCompany developerCompany = developerCompanyRepository.findById(id_dev);
        model.addAttribute("developerCompany",developerCompany);
        return "/games/developersCompany/devcom-details";
    }*/

    @GetMapping("/games/developers/{id_dev}/edit")//подготовка обновления разработчиков
    public String developersEdit(
            @ModelAttribute("developerCompany") DeveloperCompany developerCompany,//
            @PathVariable("id_dev") long id_dev,
            Model model

    )
    {
        if(!developerCompanyRepository.existsById(id_dev))
        {return "games/developersCompany/devcom-page";}
        DeveloperCompany d = developerCompanyRepository.findById(id_dev);
        model.addAttribute("developerCompany", d);
        return "games/developersCompany/devcom-edit";
    }



    @PostMapping("/games/developers/{id_dev}/edit")//обновление разработчиков
    public String developersUpdate(
            @ModelAttribute("developerCompany") @Valid DeveloperCompany developerCompany,
            BindingResult bindingResult,
            @PathVariable("id_dev") long id_dev,
            Model model

    )
    {
        List<DeveloperCompany> listDev = developerCompanyRepository.findByDevName(developerCompany.getDevName());//проверка на уникальность имени
        if(bindingResult.hasErrors() || listDev.size()>0)
        {
            if(!developerCompanyRepository.existsById(id_dev))
            {return "redirect:/games/developers";}
            DeveloperCompany d = developerCompanyRepository.findById(id_dev);
            model.addAttribute("developerCompany",d);
            return "games/developersCompany/devcom-edit";
        }

            long i = id_dev;
            DeveloperCompany developerCompany1 = developerCompanyRepository.findById(i);

            developerCompany1.setDevName(developerCompany.getDevName());


            developerCompanyRepository.save(developerCompany1);
        return "games/developersCompany/devcom-page";
    }



    @PostMapping("/games/developers/{id_dev}/delete")//удаление разработчиков
    public String developerDelete(
            @PathVariable("id_dev") long id_dev,
            Model model
    )
    {
        developerCompanyRepository.deleteById(id_dev);
        return "redirect:/games/developers";
    }


    /*@GetMapping("/games/developers/search")
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
    }*/

}
