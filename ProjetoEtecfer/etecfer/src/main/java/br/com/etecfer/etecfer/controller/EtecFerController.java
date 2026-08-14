package br.com.etecfer.etecfer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EtecFerController {

    @GetMapping({"/", "/etecfer"})
    public String index() {
        return "index";
    }
}
