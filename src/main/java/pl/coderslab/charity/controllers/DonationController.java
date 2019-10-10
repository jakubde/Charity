package pl.coderslab.charity.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.dtos.CategoryDto;
import pl.coderslab.charity.model.dtos.DonationDto;
import pl.coderslab.charity.model.dtos.InstitutionDto;
import pl.coderslab.charity.services.CategoryService;
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.InstitutionService;

import java.time.LocalDate;
import java.time.LocalTime;
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

//    @PostMapping
//    public String processAddDonation(DonationDto donationDto){
//        donationService.saveDonation(donationDto);
//        return "redirect:/donate";
//    }
    @PostMapping
    @ResponseBody
    public String processAddDonation(String categoryDtos, Integer quantity, String institutionDto, String street,
                                     String city, String zipCode, String telephoneNumber, String pickUpDate,
                                     String pickUpTime, String pickUpComment){

        List<CategoryDto> categoryDtoList = categoryService.stringWithIdsToCategoryDtoList(categoryDtos);
        InstitutionDto instDto = institutionService.findById(Long.parseLong(institutionDto));

        DonationDto donationDto = new DonationDto();

        donationDto.setCategoryDtos(categoryDtoList);
        donationDto.setQuantity(quantity);
        donationDto.setInstitutionDto(instDto);
        donationDto.setStreet(street);
        donationDto.setCity(city);
        donationDto.setZipCode(zipCode);
        donationDto.setTelephoneNumber(telephoneNumber);
        donationDto.setPickUpDate(LocalDate.parse(pickUpDate));
        donationDto.setPickUpTime(LocalTime.parse(pickUpTime));
        donationDto.setPickUpComment(pickUpComment);

        donationService.save(donationService.saveDonation(donationDto), categoryDtoList);

        System.out.println("UWAGA!  " + instDto.getId());

        return "elo";


//        private Long id;
//        private Integer quantity;
//        private List<CategoryDto> categoryDtos = new ArrayList<>();       - OK
//        private InstitutionDto institutionDto;
//        private String street;
//        private String city;
//        private String zipCode;
//        private String telephoneNumber;
//        private LocalDate pickUpDate;
//        private LocalTime pickUpTime;
//        private String pickUpComment;




    }

}

