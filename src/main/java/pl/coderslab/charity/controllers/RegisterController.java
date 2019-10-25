package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import pl.coderslab.charity.model.dtos.UserDto;
import pl.coderslab.charity.services.EmailService;
import pl.coderslab.charity.services.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;
    private final EmailService emailService;

    public RegisterController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping
    public String prepareAddUser(Model model){

        model.addAttribute("user", new UserDto());
        model.addAttribute("locale", "pl");

        return "register";
    }

    @PostMapping
    public String processAddUser(UserDto userDto, String locale){

        userService.saveUser(userDto);

        String subject = "";
        String text = "";

        if(locale.equals("pl")){
            subject = "Potwierdzenie założenia konta";

            text = "Witaj " + userDto.getFirstName() + ",\n\n" +

                    "Dziękujemy za rejestrację.\n\n" +

                    "Zespół \"Oddam w dobre ręce\"\n\n" +

                    "Pomagając innym, pomagamy sobie, bo wszystko, co dajemy, zatacza koło i wraca do nas.\n" +
                    "~Flora Edwards";

        }
        if(locale.equals("en")){
            subject = "Account confirmation";

            text = "Hello " + userDto.getFirstName() + ",\n\n" +

                    "Thank you for signing up.\n\n" +

                    "\"Leave it in good hands\" team.\n\n" +

                    "In helping others, we shall help ourselves, for whatever good we give out completes the circle and comes back to us.\n" +
                    "~Flora Edwards";
        }

        emailService.sendSimpleMessage(userDto.getEmail(), subject, text);
        return "redirect:/";
    }
}
