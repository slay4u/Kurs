package com.company.kurs.web;

import com.company.kurs.domain.Coach;
import com.company.kurs.domain.Comanda;
import com.company.kurs.pdf.ComandaPDFExporter;
import com.company.kurs.query.ComandaAmount;
import com.company.kurs.service.ComandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ComandaController {
    @Autowired private ComandaService service;

    @GetMapping("/comanda")
    public String showComandaList(Model model){
        List<Comanda> listComanda = service.listAll();
        model.addAttribute("listComanda", listComanda);
        List<Coach> listCoach = service.getCoach();
        model.addAttribute("listCoach", listCoach);
        return "comanda";
    }

    @GetMapping("/comanda/new")
    public String showNewForm(Model model){
        model.addAttribute("comanda", new Comanda());
        model.addAttribute("pageTitle", "Add New Comanda");
        return "comanda_form";
    }

    @PostMapping("/comanda/save")
    public String saveComanda(Comanda comanda, RedirectAttributes ra){
        service.save(comanda);
        ra.addFlashAttribute("message", "New comanda has been saved successfully!");
        return "redirect:/comanda";
    }

    @GetMapping("/comanda/edit/{idComanda}")
    public String showEditForm(@PathVariable("idComanda") Integer idComanda, Model model, RedirectAttributes ra){
        Comanda comanda = service.get(idComanda);
        model.addAttribute("comanda", comanda);
        model.addAttribute("pageTitle", "Edit Comanda (ID: "+idComanda+")");
        ra.addFlashAttribute("message", "New comanda has been saved successfully!");
        return "comanda_form";
    }

    @GetMapping("/comanda/delete/{idComanda}")
    public String deleteComanda(@PathVariable("idComanda") Integer idComanda, RedirectAttributes ra){
        service.delete(idComanda);
        ra.addFlashAttribute("message", "Запис з ID" + idComanda + " успішно видалений!");
        return "redirect:/comanda";
    }

    @GetMapping("/comanda/export")
    public void exportToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=comanda_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        List<ComandaAmount> comandaAmountList = service.getComandaAmount();

        ComandaPDFExporter exporter = new ComandaPDFExporter(comandaAmountList);
        exporter.export(response);
    }
}
