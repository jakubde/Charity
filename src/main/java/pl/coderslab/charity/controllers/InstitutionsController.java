package pl.coderslab.charity.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.dtos.InstitutionDto;
import pl.coderslab.charity.services.InstitutionService;

@Controller
@RequestMapping("/institutions")
public class InstitutionsController {

    private final InstitutionService institutionService;

    public InstitutionsController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping
    public String institutionsList(Model model) {
        model.addAttribute("institutionDtos", institutionService.getList());
        return "/admin/institutions/list";
    }

    @GetMapping("/edit")
    public String institutionsEditableList(Model model) {
        model.addAttribute("institutionDtos", institutionService.getList());
        return "/admin/institutions/editableList";
    }

    @GetMapping("/add")
    public String prepareAddInstitutionPage(Model model) {
        model.addAttribute("institutionDto", new InstitutionDto());
        return "admin/institutions/add";
    }

    @PostMapping("/add")
    public String processAddInstitutionPage(InstitutionDto institutionDto) {
        institutionService.saveInstitution(institutionDto);
        return "redirect:/institutions";
    }

    @GetMapping("/edit/{id}")
    public String prepareEditInstitutionPage(@PathVariable String id, Model model) {
        InstitutionDto editedInstitution = institutionService.findById(Long.parseLong(id));
        model.addAttribute("institutionDto", editedInstitution);
        return "admin/institutions/edit";
    }

    @PostMapping("edit/{id}")
    public String processEditInstitutionPage(InstitutionDto institutionDto) {
        institutionService.updateInstitution(institutionDto);
        return "redirect:/institutions/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteInstitution(@PathVariable String id, Model model) {
        try {
            institutionService.deleteInstitution(Long.parseLong(id));
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("dataViolationFlag", "error");
            model.addAttribute("institutionDtos", institutionService.getList());
            return "/admin/institutions/editableList";
        }
        return "redirect:/institutions/edit";
    }
}
