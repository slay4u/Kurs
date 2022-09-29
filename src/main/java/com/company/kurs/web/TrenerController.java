package com.company.kurs.web;

import com.company.kurs.domain.Comanda;
import com.company.kurs.domain.Trener;
import com.company.kurs.repository.ComandaRep;
import com.company.kurs.service.ComandaService;
import com.company.kurs.service.TrenerService;
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
public class TrenerController {
    @Autowired private TrenerService service;

    @Autowired
    private ComandaRep comandaRep;

    @GetMapping("/trener")
    public String showTrenerList(Model model){
        List<Trener> listTrener = service.listAll();
        model.addAttribute("listTrener", listTrener);
        return "trener";
    }

    @GetMapping("/trener/new")
    public String showNewForm(Model model){
        List<Comanda> listComanda = ComandaService.makeCollection(comandaRep.findAll());
        model.addAttribute("listComanda", listComanda);
        model.addAttribute("trener", new Trener());
        model.addAttribute("pageTitle", "Add New Trener");
        return "trener_form";
    }

    @PostMapping("/trener/save")
    public String saveTrener(Trener trener, RedirectAttributes ra){
        service.save(trener);
        ra.addFlashAttribute("message", "New trener has been saved successfully!");
        return "redirect:/trener";
    }

    @GetMapping("/trener/edit/{idTrener}")
    public String showEditForm(@PathVariable("idTrener") Integer idTrener, Model model, RedirectAttributes ra){
        List<Comanda> listComanda = ComandaService.makeCollection(comandaRep.findAll());
        model.addAttribute("listComanda", listComanda);
        Trener trener = service.get(idTrener);
        model.addAttribute("trener", trener);
        model.addAttribute("pageTitle", "Edit Trener (ID: "+idTrener+")");
        ra.addFlashAttribute("message", "New trener has been saved successfully!");
        return "trener_form";
    }

    @GetMapping("/trener/delete/{idTrener}")
    public String deleteTrener(@PathVariable("idTrener") Integer idTrener, RedirectAttributes ra){
        service.delete(idTrener);
        ra.addFlashAttribute("message", "Запис з ID" + idTrener + " успішно видалений!");
        return "redirect:/trener";
    }

    @RequestMapping("/trener/searchByComanda")
    public String findByComanda(Trener trener, Model model, String name_c){
        if(name_c != null){
            List<Trener> listTrener = service.getTrenerByComanda(name_c);
            model.addAttribute("listTrener", listTrener);
        } else {
            List<Trener> listTrener = service.listAll();
            model.addAttribute("listTrener", listTrener);
        }
        return "trener";
    }

    @RequestMapping("/trener/searchByLetter")
    public String findByLetter(Trener trener, Model model, String pibtrener){
        if(pibtrener != null){
            List<Trener> listTrener = service.getTrenerByLetter(pibtrener);
            model.addAttribute("listTrener", listTrener);
        } else {
            List<Trener> listTrener = service.listAll();
            model.addAttribute("listTrener", listTrener);
        }
        return "trener";
    }
}
