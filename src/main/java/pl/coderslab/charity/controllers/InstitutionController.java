package pl.coderslab.charity.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.services.InstitutionService;

@Controller
@RequestMapping("/institutions")
public class InstitutionController {

    private final InstitutionService institutionService;

    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping
    public String prepareAllInstitutionsPage(Model model) {

        model.addAttribute("institutions", institutionService.getList());
        return "/admin/institutions/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteInstitution(@PathVariable String id) {
        institutionService.deleteInstitution(Long.parseLong(id));
        return "redirect:/institutions";
    }
}
