package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String admin() {
        return "admin/admin";
    }
}
