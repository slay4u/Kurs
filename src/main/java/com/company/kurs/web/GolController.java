package com.company.kurs.web;

import com.company.kurs.domain.Game;
import com.company.kurs.domain.Gol;
import com.company.kurs.domain.Player;
import com.company.kurs.repository.GameRep;
import com.company.kurs.repository.PlayerRep;
import com.company.kurs.service.GameService;
import com.company.kurs.service.GolService;
import com.company.kurs.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class GolController {
    @Autowired
    private GolService service;

    @Autowired
    private PlayerRep playerRep;

    @Autowired
    private GameRep gameRep;

    @GetMapping("/gol")
    public String showGolList(Model model){

        List<Gol> listGol = service.listAll();
        model.addAttribute("listGol", listGol);

        return "gol";
    }

    @GetMapping("/gol/new")
    public String showNewForm(Model model){
        List<Game> listGame = GameService.makeCollection(gameRep.findAll());
        model.addAttribute("listGame", listGame);
        List<Player> listPlayer = PlayerService.makeCollection(playerRep.findAll());
        model.addAttribute("listPlayer", listPlayer);
        model.addAttribute("gol", new Gol());
        model.addAttribute("pageTitle", "Add New Gol");
        return "gol_form";
    }

    @PostMapping("/gol/save")
    public String saveGol(Gol gol, RedirectAttributes ra){
        service.save(gol);
        ra.addFlashAttribute("message", "New gol has been saved successfully!");
        return "redirect:/gol";
    }

    @GetMapping("/gol/edit/{idGol}")
    public String showEditForm(@PathVariable("idGol") Integer idGol, Model model, RedirectAttributes ra){
        List<Game> listGame = GameService.makeCollection(gameRep.findAll());
        model.addAttribute("listGame", listGame);
        List<Player> listPlayer = PlayerService.makeCollection(playerRep.findAll());
        model.addAttribute("listPlayer", listPlayer);
        Gol gol = service.get(idGol);
        model.addAttribute("gol", gol);
        model.addAttribute("pageTitle", "Edit Gol (ID: "+idGol+")");
        ra.addFlashAttribute("message", "New gol has been saved successfully!");
        return "gol_form";
    }

    @GetMapping("/gol/delete/{idGol}")
    public String deleteGol(@PathVariable("idGol") Integer idGol, RedirectAttributes ra){
        service.delete(idGol);
        ra.addFlashAttribute("message", "Запис з ID" + idGol + " успішно видалений!");
        return "redirect:/gol";
    }
}
