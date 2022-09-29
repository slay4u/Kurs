package com.company.kurs.web;

import com.company.kurs.domain.Comanda;
import com.company.kurs.domain.Player;
import com.company.kurs.repository.ComandaRep;
import com.company.kurs.service.ComandaService;
import com.company.kurs.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
public class PlayerController {
    @Autowired
    private PlayerService service;

    @Autowired
    private ComandaRep comandaRep;

    @GetMapping("/player")
    public String showPlayerList(Model model){

        List<Player> listPlayer = service.listAll();
        model.addAttribute("listPlayer", listPlayer);

        return "player";
    }

    @GetMapping("/player/new")
    public String showNewForm(Model model){
        List<Comanda> listComanda = ComandaService.makeCollection(comandaRep.findAll());
        model.addAttribute("listComanda", listComanda);
        model.addAttribute("player", new Player());
        model.addAttribute("pageTitle", "Add New Player");
        return "player_form";
    }

    @PostMapping("/player/save")
    public String savePlayer(Player player, RedirectAttributes ra){
        service.save(player);
        ra.addFlashAttribute("message", "New player has been saved successfully!");
        return "redirect:/player";
    }

    @GetMapping("/player/edit/{idPlayer}")
    public String showEditForm(@PathVariable("idPlayer") Integer idPlayer, Model model, RedirectAttributes ra){
        List<Comanda> listComanda = ComandaService.makeCollection(comandaRep.findAll());
        model.addAttribute("listComanda", listComanda);
        Player player = service.get(idPlayer);
        model.addAttribute("player", player);
        model.addAttribute("pageTitle", "Edit Player (ID: "+idPlayer+")");
        ra.addFlashAttribute("message", "New player has been saved successfully!");
        return "player_form";
    }

    @GetMapping("/player/delete/{idPlayer}")
    public String deletePlayer(@PathVariable("idPlayer") Integer idPlayer, RedirectAttributes ra){
        service.delete(idPlayer);
        ra.addFlashAttribute("message", "Запис з ID" + idPlayer + " успішно видалений!");
        return "redirect:/player";
    }

    @RequestMapping("/player/searchByComanda")
    public String findByComanda(Player player, Model model, String name_c){
        if(name_c != null){
            List<Player> listPlayer = service.getPlayerByComanda(name_c);
            model.addAttribute("listPlayer", listPlayer);
        } else {
            List<Player> listPlayer = service.listAll();
            model.addAttribute("listPlayer", listPlayer);
        }
        return "player";
    }

    @RequestMapping("/player/searchByLetter")
    public String findByLetter(Player player, Model model, String pibPlayer){
        if(pibPlayer != null){
            List<Player> listPlayer = service.getPlayerByLetter(pibPlayer);
            model.addAttribute("listPlayer", listPlayer);
        } else {
            List<Player> listPlayer = service.listAll();
            model.addAttribute("listPlayer", listPlayer);
        }
        return "player";
    }
}
