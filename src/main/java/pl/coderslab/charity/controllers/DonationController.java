package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.dtos.CategoryDto;
import pl.coderslab.charity.model.dtos.DonationDto;
import pl.coderslab.charity.model.dtos.InstitutionDto;
import pl.coderslab.charity.services.CategoryService;
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.InstitutionService;

import java.util.List;


@Controller
@RequestMapping("/donate")
public class DonationController {

    private final CategoryService categoryService;
    private final DonationService donationService;
    private final InstitutionService institutionService;

    public DonationController(CategoryService categoryService, DonationService donationService, InstitutionService institutionService) {
        this.categoryService = categoryService;
        this.donationService = donationService;
        this.institutionService = institutionService;
    }

    @GetMapping
    public String prepareAddDonation(Model model){

        List<InstitutionDto> institutionDtos = institutionService.getList();
        List<CategoryDto> categoryDtos = categoryService.getList();

        model.addAttribute("categories", categoryDtos);
        model.addAttribute("donation", new DonationDto());
        model.addAttribute("institutions", institutionDtos);

        return "form";
    }

    @PostMapping
    public String processAddDonation(DonationDto donationDto){

        donationService.saveDonation(donationDto);

        return "redirect:/";
    }
}

