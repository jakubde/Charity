package pl.coderslab.charity.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.dtos.InstitutionDto;
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.InstitutionService;
import pl.coderslab.charity.services.UserService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;

    public HomeController(InstitutionService institutionService, DonationService donationService, UserService userService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
    }


    @GetMapping
    public String homeAction(Model model) {
        log.debug("log inside controller");
        List<InstitutionDto> institutionDtos = institutionService.getList();
        Integer donationSum = donationService.donationSum();
        int donatedInstitutionsSum = donationService.distinctInstitutionsCount();

        model.addAttribute("institutions", institutionDtos);
        model.addAttribute("donationSum", donationSum);
        model.addAttribute("donatedInstitutionsSum", donatedInstitutionsSum);
        return "index";
    }

    @GetMapping("adminPanel")
    public String adminPanel(Model model) throws ParseException {
        Integer donationSum = donationService.donationSum();
        int donatedInstitutionsSum = donationService.distinctInstitutionsCount();
        Integer numberOfUsers = userService.countAllUsers();
        Double donationsPerDay = donationService.getDonationsPerDay();
        List<String> donationsSumsInLastTwelveMonths = donationService.donationsSumsInLastTwelveMonths();
        List<String> chartLabels = donationService.lastTwelveMonthsNames();
        List<String> pieChartLabels = donationService.pieChartLabels();
        List<Long> pieChartValues = donationService.pieChartValues();

//        String as = pieChartLabels.toString().replaceAll("\"", "").replaceAll("“", "")
//                .replaceAll("”", "");

        model.addAttribute("donationSum", donationSum);
        model.addAttribute("donatedInstitutionsSum", donatedInstitutionsSum);
        model.addAttribute("numberOfUsers", numberOfUsers);
        model.addAttribute("donationsPerDay", donationsPerDay);
        model.addAttribute("donationsSumsInLastTwelveMonths", donationsSumsInLastTwelveMonths);
        model.addAttribute("chartLabels", chartLabels);
        model.addAttribute("pieChartLabels", pieChartLabels);
        model.addAttribute("pieChartValues", pieChartValues);

        System.out.println(pieChartLabels);

        System.out.println(pieChartValues);

        return "admin/adminPanel";
    }
}
