package com.company.kurs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String showHomePage(){
        System.out.println("main controller");
        return "index";
    }

    @GetMapping("/403")
    public String error403(){
        return "403";
    }
}
