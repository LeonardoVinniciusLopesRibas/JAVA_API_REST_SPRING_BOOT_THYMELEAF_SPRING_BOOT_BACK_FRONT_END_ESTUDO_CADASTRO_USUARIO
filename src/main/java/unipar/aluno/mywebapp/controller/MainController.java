package unipar.aluno.mywebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String showHomePage(){
        System.out.println("PÁGINA INDEX.HTML ABERTA");
        return "index";
    }



}
