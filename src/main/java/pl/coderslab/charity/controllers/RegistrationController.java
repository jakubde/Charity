package pl.coderslab.charity.controllers;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.model.dtos.UserDto;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.entities.VerificationToken;
import pl.coderslab.charity.services.EmailService;
import pl.coderslab.charity.services.UserService;

import java.util.Calendar;
import java.util.Locale;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final EmailService emailService;
    private final ApplicationEventPublisher eventPublisher;
    private final MessageSource messageSource;

    public RegistrationController(UserService userService, EmailService emailService, ApplicationEventPublisher eventPublisher, MessageSource messageSource) {
        this.userService = userService;
        this.emailService = emailService;
        this.eventPublisher = eventPublisher;
        this.messageSource = messageSource;
    }

    @GetMapping("/register")
    public String prepareAddUser(Model model) {

        model.addAttribute("user", new UserDto());
        model.addAttribute("locale", "pl");
        model.addAttribute("error", "");

        return "register";
    }

    @PostMapping("/register")
    public ModelAndView processAddUser(
            UserDto userDto,
            String locale) {



        if (userService.findEnabledUserByEmail(userDto.getEmail()) != null) {
            ModelAndView modelAndView = new ModelAndView("/register");
            userDto.setPassword(null);
            modelAndView.addObject("user", userDto);
            modelAndView.addObject("locale", locale);
            modelAndView.addObject("error", "userAlreadyExists");
            return modelAndView;
        }

        VerificationToken verificationToken = new VerificationToken(userService.saveUser(userDto));

        userService.saveToken(verificationToken);

        String subject = "";
        String text = "";


        if (locale.equals("pl")) {
            subject = "Potwierdzenie rejestracji";

            text = "Witaj " + userDto.getFirstName() + ",\n\n" +

                    "Dziękujemy za rejestrację. Aby aktywować konto, kliknij następujący link:\n" +
                    "http://localhost:8080/confirm-account?token=" + verificationToken.getToken() + "\n\n" +

                    "Zespół \"Oddam w dobre ręce\"\n\n" +

                    "Pomagając innym, pomagamy sobie, bo wszystko, co dajemy, zatacza koło i wraca do nas.\n" +
                    "~Flora Edwards";

        }
        if (locale.equals("en")) {
            subject = "Registration confirmation";

            text = "Hello " + userDto.getFirstName() + ",\n\n" +

                    "Thank you for signing up. To activate the account click on the following link:" +
                    "http://localhost:8080/confirm-account?token=" + verificationToken.getToken() + "\n\n" +

                    "\"Leave it in good hands\" team.\n\n" +

                    "In helping others, we shall help ourselves, for whatever good we give out completes the circle and comes back to us.\n" +
                    "~Flora Edwards";
        }

        emailService.sendSimpleMessage(userDto.getEmail(), subject, text);

        ModelAndView modelAndView = new ModelAndView("/successfulRegistration");
        modelAndView.addObject("email", userDto.getEmail());

        return modelAndView;

    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
        VerificationToken token = userService.findVerificationToken(confirmationToken);

        if (token != null) {
            final User user = userService.getUser(token.getToken());
            final Calendar cal = Calendar.getInstance();
            if ((token.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
                userService.deleteToken(token);
                modelAndView.setViewName("verificationError");
            } else {
                user.setEnabled(true);
                userService.saveUser(user);
                modelAndView.setViewName("accountVerified");
            }
        } else {
            modelAndView.setViewName("verificationError");
        }

        return modelAndView;
    }


}

