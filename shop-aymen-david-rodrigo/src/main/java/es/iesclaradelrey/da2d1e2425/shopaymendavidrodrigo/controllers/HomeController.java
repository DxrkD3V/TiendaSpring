package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String mostrarIndex() {
        return "index";
    }
}