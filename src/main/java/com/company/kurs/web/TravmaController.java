package com.company.kurs.web;

import com.company.kurs.domain.Player;
import com.company.kurs.domain.Travma;
import com.company.kurs.repository.PlayerRep;
import com.company.kurs.service.PlayerService;
import com.company.kurs.service.TravmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class TravmaController {
    @Autowired
    private TravmaService service;

    @Autowired
    private PlayerRep playerRep;

    @GetMapping("/travma")
    public String showTravmaList(Model model){
        List<Travma> listTravma = service.listAll();
        model.addAttribute("listTravma", listTravma);
        double amount = service.getCount();
        model.addAttribute("amount", amount);
        return "travma";
    }

    @GetMapping("/travma/new")
    public String showNewForm(Model model){
        List<Player> listPlayer = PlayerService.makeCollection(playerRep.findAll());
        model.addAttribute("listPlayer", listPlayer);
        model.addAttribute("travma", new Travma());
        model.addAttribute("pageTitle", "Add New Travma");
        return "travma_form";
    }

    @PostMapping("/travma/save")
    public String saveTravma(Travma travma, RedirectAttributes ra){
        service.save(travma);
        ra.addFlashAttribute("message", "New travma has been saved successfully!");
        return "redirect:/travma";
    }

    @GetMapping("/travma/edit/{idTravma}")
    public String showEditForm(@PathVariable("idTravma") Integer idTravma, Model model, RedirectAttributes ra){
        List<Player> listPlayer = PlayerService.makeCollection(playerRep.findAll());
        model.addAttribute("listPlayer", listPlayer);
        Travma travma = service.get(idTravma);
        model.addAttribute("travma", travma);
        model.addAttribute("pageTitle", "Edit Travma (ID: "+idTravma+")");
        ra.addFlashAttribute("message", "New travma has been saved successfully!");
        return "travma_form";
    }

    @GetMapping("/travma/delete/{idTravma}")
    public String deleteTravma(@PathVariable("idTravma") Integer idTravma, RedirectAttributes ra){
        service.delete(idTravma);
        ra.addFlashAttribute("message", "Запис з ID" + idTravma + " успішно видалений!");
        return "redirect:/travma";
    }

    @RequestMapping("/travma/searchByDate")
    public String findByDate(Travma travma, Model model, java.sql.Date date1, java.sql.Date date2){
        if(date1 != null && date2 != null){
            List<Travma> listTravma = service.getTravmaByDateTravma(date1, date2);
            model.addAttribute("listTravma", listTravma);
        } else {
            List<Travma> listTravma = service.listAll();
            model.addAttribute("listTravma", listTravma);
        }
        return "travma";
    }
}
