package pl.coderslab.charity.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.dtos.InstitutionDto;
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.InstitutionService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;

    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }


    @GetMapping
    public String homeAction(Model model){
        log.debug("log inside controller");
        List<InstitutionDto> institutionDtos = institutionService.getList();
        Integer donationSum = donationService.donationSum();
        int donatedInstitutionsSum = donationService.distinctInstitutionsCount();

        model.addAttribute("institutions", institutionDtos);
        model.addAttribute("donationSum", donationSum);
        model.addAttribute("donatedInstitutionsSum", donatedInstitutionsSum);
        System.out.println(System.getenv("SMTPPASS"));
        return "index";
    }
}
