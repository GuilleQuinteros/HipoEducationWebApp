package com.hipo.eduapp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortarController {


    @GetMapping("/")  // Acá es donde realizamos el mapeo
    public  String index(){
        return "index.html"; // Acá es que retornamos con el método.
    }
}
