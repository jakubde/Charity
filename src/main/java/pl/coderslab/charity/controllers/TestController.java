package pl.coderslab.charity.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping
    public String testPage(){
        return "index_original";

    }
}
