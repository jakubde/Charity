package pl.coderslab.charity.controllers;


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

    @GetMapping("/add")
    public String prepareAddInstitutionPage(Model model){
        model.addAttribute("institution", new InstitutionDto());
        return "admin/institutions/add";
    }

    @PostMapping("/add")
    public String processAddInstitutionPage(InstitutionDto institutionDto){
        institutionService.saveInstitution(institutionDto);
        return "redirect:/institutions";
    }

    @GetMapping("/edit/{id}")
    public String prepareEditInstitutionPage(@PathVariable String id, Model model){
        InstitutionDto editedInstitution = institutionService.findById(Long.parseLong(id));
        model.addAttribute("institution", editedInstitution);
        if(editedInstitution == null){
            return "institutions/edit/{id}";
        }
        return "admin/institutions/add";
    }



    //    @GetMapping
    //    public String prepareEditLicence(@PathVariable String id, Model model) {
    //        Licence editedLicence = licenceRepository.findAllById(Long.parseLong(id));
    //        model.addAttribute("licence", editedLicence);
    //        if(editedLicence == null){
    //            return "licences/add/{id}";
    //        }
    //        return "admin/licences/add";
    //    }
    //
    //    @PostMapping
    //    public String processEditLicence(Licence licence, BindingResult result) {
    //        if(result.hasErrors()){
    //            return "admin/licences/add";
    //        }
    //        if(licence.getId() != null){
    //            licenceRepository.save(licence);
    //        }
    //        return "redirect:/licences";
    //    }

}
