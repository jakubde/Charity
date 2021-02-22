package pl.coderslab.charity.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.dtos.InstitutionDto;
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.InstitutionService;
import pl.coderslab.charity.services.UserService;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;
    private final MessageSource messageSource;

    public HomeController(InstitutionService institutionService, DonationService donationService, UserService userService, MessageSource messageSource) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public String homeAction(Model model) {
        List<List<String>> institutionNameAndDescriptionInCorrespondingLanguage = institutionService.getInstitutionNameAndDescriptionInCorrespondingLanguage();
        Integer donationSum = donationService.donationSum();
        int donatedInstitutionsSum = donationService.distinctInstitutionsCount();

        model.addAttribute("institutions", institutionNameAndDescriptionInCorrespondingLanguage);
        model.addAttribute("donationSum", donationSum);
        model.addAttribute("donatedInstitutionsSum", donatedInstitutionsSum);

        return "index";
    }

    @GetMapping("/adminPanel")
    public String adminPanel(Model model) throws ParseException {
        Locale locale = LocaleContextHolder.getLocale();
        String language = locale.getLanguage();
        Integer donationSum = donationService.donationSum();
        int donatedInstitutionsSum = donationService.distinctInstitutionsCount();
        Integer numberOfUsers = userService.countAllUsers();
        Double donationsPerDay = donationService.getDonationsPerDay();
        List<String> donationsSumsInLastTwelveMonths = donationService.donationsSumsInLastTwelveMonths();
        List<String> donationsInLastTwelveMonths = donationService.donationsInLastTwelveMonths();
        List<String> chartLabels = donationService.lastTwelveMonthsNames(locale);
        List<String> pieChartLabels = donationService.chartLabels(language);
        List<Long> pieChartValues = donationService.pieChartValues();

        model.addAttribute("donationSum", donationSum);
        model.addAttribute("donatedInstitutionsSum", donatedInstitutionsSum);
        model.addAttribute("numberOfUsers", numberOfUsers);
        model.addAttribute("donationsPerDay", donationsPerDay);
        model.addAttribute("donationsSumsInLastTwelveMonths", donationsSumsInLastTwelveMonths);
        model.addAttribute("donationsInLastTwelveMonths", donationsInLastTwelveMonths);
        model.addAttribute("chartLabels", chartLabels);
        model.addAttribute("pieChartLabels", pieChartLabels);
        model.addAttribute("pieChartValues", pieChartValues);

        return "admin/adminPanel";
    }
}
