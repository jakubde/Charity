package pl.coderslab.charity.controllers;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.model.dtos.UserDto;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.entities.VerificationToken;
import pl.coderslab.charity.services.UserService;

import javax.validation.Valid;
import java.util.Calendar;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String prepareAddUser(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "registration/register";
    }

    @PostMapping("/register")
    public ModelAndView processAddUser(@Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/registration/register");
            return modelAndView;
        } else {
            userService.createNewUser(userDto);
            userService.sendRegistrationEmailWithToken(LocaleContextHolder.getLocale(), userDto);

            ModelAndView modelAndView = new ModelAndView("registration/successfulRegistration");
            modelAndView.addObject("email", userDto.getEmail());
            return modelAndView;
        }
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String token) {
        VerificationToken verificationToken = userService.findVerificationToken(token);

        if (verificationToken != null) {
            final User user = userService.getUserByVerificationToken(verificationToken.getToken());
            final Calendar cal = Calendar.getInstance();
            if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
                userService.deleteVerificationToken(verificationToken);
                modelAndView.setViewName("registration/expiredVerificationTokenError");
            } else {
                user.setEnabled(true);
                userService.saveUser(user);
                modelAndView.setViewName("registration/accountVerified");
            }
        } else {
            modelAndView.setViewName("registration/badVerificationTokenError");
        }

        return modelAndView;
    }
}

