package pl.coderslab.charity.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.dtos.DonationStatusDto;
import pl.coderslab.charity.services.DonationStatusService;

@Controller
@RequestMapping("/donationStatus")
public class DonationStatusesController {

    private final DonationStatusService donationStatusService;

    public DonationStatusesController(DonationStatusService donationStatusService) {
        this.donationStatusService = donationStatusService;
    }

    @GetMapping
    public String prepareDonationStatusList(Model model) {
        model.addAttribute("donationStatusDtoList", donationStatusService.getList());
        return "/admin/donationStatuses/list";
    }

    @GetMapping("/edit")
    public String prepareDonationStatusEditableList(Model model) {
        model.addAttribute("donationStatusDtoList", donationStatusService.getList());
        return "/admin/donationStatuses/editableList";
    }

    @GetMapping("/delete/{id}")
    public String deleteDonationStatus(@PathVariable Long id, Model model) {
        try {
            donationStatusService.deleteDonationStatus(id);
            return "redirect:/donationStatus/edit";
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("donationStatusDtoList", donationStatusService.getList());
            model.addAttribute("dataViolationFlag", "error");
            return "/admin/donationStatuses/editableList";
        }
    }

    @GetMapping("/add")
    public String prepareAddDonationStatus(Model model) {
        model.addAttribute("donationStatusDto", new DonationStatusDto());
        return "/admin/donationStatuses/add";
    }

    @PostMapping("/add")
    public String processAddDonationStatus(DonationStatusDto donationStatusDto) {
        donationStatusService.saveDonationStatus(donationStatusDto);
        return "redirect:/donationStatus";
    }

    @GetMapping("/edit/{id}")
    public String prepareEditDonationStatus(@PathVariable Long id, Model model) {
        model.addAttribute("donationStatusDto", donationStatusService.findById(id));
        return "/admin/donationStatuses/edit";
    }

    @PostMapping("/edit/{id}")
    public String processEditDonationStatus(DonationStatusDto donationStatusDto) {
        donationStatusService.updateDonationStatus(donationStatusDto);
        return "redirect:/donationStatus/edit";
    }

}



















