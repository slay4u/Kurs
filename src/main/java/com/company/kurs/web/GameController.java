package com.company.kurs.web;

import com.company.kurs.domain.Comanda;
import com.company.kurs.domain.Game;
import com.company.kurs.domain.Player;
import com.company.kurs.repository.ComandaRep;
import com.company.kurs.service.ComandaService;
import com.company.kurs.service.GameService;
import com.company.kurs.service.PlayerService;
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
public class GameController {
    @Autowired
    private GameService service;

    @Autowired
    private ComandaRep comandaRep;

    @Autowired private PlayerService playerService;

    @GetMapping("/game")
    public String showGameList(Model model){
        List<Game> listGame = service.listAll();
        model.addAttribute("listGame", listGame);
        double amount = service.getCount();
        model.addAttribute("amount", amount);
        List<Game> listLast = service.getLast();
        model.addAttribute("listLast", listLast);
        return "game";
    }

    @GetMapping("/game/new")
    public String showNewForm(Model model){
        List<Comanda> listComanda = ComandaService.makeCollection(comandaRep.findAll());
        model.addAttribute("listComanda", listComanda);
        List<Player> listPlayer = playerService.listAll();
        model.addAttribute("listPlayer", listPlayer);
        model.addAttribute("game", new Game());
        model.addAttribute("pageTitle", "Add New Game");
        return "game_form";
    }

    @PostMapping("/game/save")
    public String saveGame(Game game, RedirectAttributes ra){
        service.save(game);
        ra.addFlashAttribute("message", "New game has been saved successfully!");
        return "redirect:/game";
    }

    @GetMapping("/game/edit/{idGame}")
    public String showEditForm(@PathVariable("idGame") Integer idGame, Model model, RedirectAttributes ra){
        List<Comanda> listComanda = ComandaService.makeCollection(comandaRep.findAll());
        model.addAttribute("listComanda", listComanda);
        Game game = service.get(idGame);
        model.addAttribute("game", game);
        model.addAttribute("pageTitle", "Edit Game (ID: "+idGame+")");
        List<Player> listPlayer = playerService.listAll();
        model.addAttribute("listPlayer", listPlayer);
        ra.addFlashAttribute("message", "New game has been saved successfully!");
        return "game_form";
    }

    @GetMapping("/game/delete/{idGame}")
    public String deleteGame(@PathVariable("idGame") Integer idGame, RedirectAttributes ra){
        service.delete(idGame);
        ra.addFlashAttribute("message", "Запис з ID" + idGame + " успішно видалений!");
        return "redirect:/game";
    }

    @RequestMapping("/game/searchByDate")
    public String findByDate(Game game, Model model, java.sql.Date date1, java.sql.Date date2){
        if(date1 != null && date2 != null){
            List<Game> listGame = service.getGameByDate(date1, date2);
            model.addAttribute("listGame", listGame);
        } else {
            List<Game> listGame = service.listAll();
            model.addAttribute("listGame", listGame);
        }
        return "game";
    }
}
