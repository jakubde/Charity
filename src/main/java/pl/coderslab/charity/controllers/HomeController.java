package pl.coderslab.charity.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.dtos.InstitutionDto;
import pl.coderslab.charity.model.entities.Institution;
import pl.coderslab.charity.model.repositories.InstitutionRepository;
import pl.coderslab.charity.services.InstitutionService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

    private final InstitutionService institutionService;
    private final InstitutionRepository institutionRepository;

    public HomeController(InstitutionService institutionService, InstitutionRepository institutionRepository) {
        this.institutionService = institutionService;
        this.institutionRepository = institutionRepository;
    }


    @GetMapping
    public String homeAction(Model model){
        log.debug("log inside controller");
        List<InstitutionDto> institutionDtos = institutionService.getList();
        model.addAttribute("institutions", institutionDtos);
        return "index";
    }
}
