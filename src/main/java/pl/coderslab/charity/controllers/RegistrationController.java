package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.model.dtos.UserDto;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.entities.VerificationToken;
import pl.coderslab.charity.services.UserService;

import java.util.Calendar;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
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

        VerificationToken verificationToken;

        if (userService.findUserbyEmail(userDto.getEmail()) != null) {
            verificationToken = new VerificationToken(userService.dtoToEntity(userService.findUserbyEmail(userDto.getEmail())));
        }
        else {
            verificationToken = new VerificationToken(userService.createNewAccount(userDto));
        }

        userService.saveToken(verificationToken);

        userService.sendEmailConfirmationMailInAppropriateLanguage(locale, userDto, verificationToken);

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

