package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.dtos.DonationDto;
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.DonationStatusService;
import pl.coderslab.charity.services.InstitutionService;
import pl.coderslab.charity.services.UserService;

@Transactional
@Controller
@RequestMapping("/donations")
public class DonationsController {

    private final DonationService donationService;
    private final UserService userService;
    private final InstitutionService institutionService;
    private final DonationStatusService donationStatusService;

    public DonationsController(DonationService donationService, UserService userService, InstitutionService institutionService, DonationStatusService donationStatusService) {
        this.donationService = donationService;
        this.userService = userService;
        this.institutionService = institutionService;
        this.donationStatusService = donationStatusService;
    }

    @GetMapping("/")
    public String showDonationList(Model model) {
        model.addAttribute("donationDtoList", donationService.getList());
        model.addAttribute("categoryMap", donationService.getCategoryMap());
        model.addAttribute("institutionMap", donationService.getInstitutionMap());
        model.addAttribute("donationStatusMap", donationService.getDonationStatusMap());
        return "/admin/donations/list";
    }

    @GetMapping("/edit")
    public String editableDonationList(Model model) {
        model.addAttribute("donationDtoList", donationService.getList());
        model.addAttribute("categoryMap", donationService.getCategoryMap());
        model.addAttribute("institutionMap", donationService.getInstitutionMap());
        model.addAttribute("donationStatusMap", donationService.getDonationStatusMap());
        return "/admin/donations/editableList";
    }

    @GetMapping("/add")
    public String prepareAddDonation(Model model) {
        model.addAttribute("donationDto", new DonationDto());
        model.addAttribute("userEmailList", userService.getUserEmailList());
        model.addAttribute("institutionNameListAsString", institutionService.getInstitutionNameListAsString());
        model.addAttribute("donationStatusMap", donationStatusService.getDonationStatusMap());
        model.addAttribute("categoryMap", donationService.getCategoryMap());
        return "/admin/donations/add";
    }

    @PostMapping("/add")
    public String processAddDonation(String userEmail,
                                     String categoryIdListAsString,
                                     String institutionName,
                                     Long statusId,
                                     DonationDto donationDto) {

        donationService.createDonation(userEmail, categoryIdListAsString, institutionName, statusId, donationDto);
        return "redirect:/donations/";
    }

    @GetMapping("/edit/{id}")
    public String prepareEditDonation(@PathVariable Long id, Model model) {
        DonationDto donationDto = donationService.findDonationById(id);
        model.addAttribute("donationDto", donationDto);
        model.addAttribute("userEmailList", userService.getUserEmailList());
        model.addAttribute("institutionNameListAsString", institutionService.getInstitutionNameListAsString());
        model.addAttribute("categoryMap", donationService.getCategoryMap());
        model.addAttribute("institutionMap", donationService.getInstitutionMap());
        model.addAttribute("donationStatusMap", donationService.getDonationStatusMap());
        model.addAttribute("userEmail", userService.getUserEmailById(donationDto.getUserId()));
        return "/admin/donations/edit";
    }

    @PostMapping("/edit/{id}")
    public String processEditDonation(String userEmail,
                                      String categoryIdListAsString,
                                      String institutionName,
                                      Long statusId,
                                      DonationDto donationDto) {
        donationService.createDonation(userEmail, categoryIdListAsString, institutionName, statusId, donationDto);
        return "redirect:/donations/edit";
    }

    @GetMapping("/status/{id}")
    public String prepareChangeDonationStatus(@PathVariable Long id, Model model) {
        DonationDto donationDto = donationService.findDonationById(id);
        model.addAttribute("donationDto", donationDto);
        model.addAttribute("categoryMap", donationService.getCategoryMap());
        model.addAttribute("institutionMap", donationService.getInstitutionMap());
        model.addAttribute("donationStatusMap", donationService.getDonationStatusMap());
        model.addAttribute("userEmail", userService.getUserEmailById(donationDto.getUserId()));
        return "/admin/donations/status";
    }

    @PostMapping("/status/{id}")
    public String processChangeDonationStatus(@PathVariable Long id, Long donationStatusId) {
        donationService.changeDonationStatus(id, donationStatusId);
        return "redirect:/donations/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteDonation(@PathVariable Long id) {
        donationService.deleteDonation(id);
        return "redirect:/donations/edit";
    }
}