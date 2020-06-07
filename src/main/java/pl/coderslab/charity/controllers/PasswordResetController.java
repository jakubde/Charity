package pl.coderslab.charity.controllers;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.model.dtos.PasswordResetDto;
import pl.coderslab.charity.model.dtos.UserDto;
import pl.coderslab.charity.model.entities.PasswordResetToken;
import pl.coderslab.charity.services.UserService;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

@Controller
@RequestMapping("/password-reset")
public class PasswordResetController {

    private final UserService userService;

    public PasswordResetController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/request")
    public String preparePasswordResetRequest(Model model) {
        return "passwordReset/passwordResetRequestForm";
    }

    @PostMapping("/request")
    //TODO - Validation:
    // NotNull
    // EmailExists
    public ModelAndView processPasswordResetRequest(String userEmail) {

        PasswordResetToken passwordResetToken = new PasswordResetToken(userService.dtoToEntity(userService.findUserByEmail(userEmail)));
        userService.savePasswordResetToken(passwordResetToken);

        UserDto userDto = userService.findUserByEmail(userEmail);

        userService.sendPasswordResetEmailInAppropriateLanguage(LocaleContextHolder.getLocale(), userDto, passwordResetToken);

        ModelAndView modelAndView = new ModelAndView("passwordReset/passwordResetRequestSentSuccessfully");
        modelAndView.addObject("email", userEmail);

        return modelAndView;
    }

    @RequestMapping(value = "/token-validation", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmPasswordResetRequest(ModelAndView modelAndView, @RequestParam("token") String token) {
        PasswordResetToken passwordResetToken = userService.findPasswordResetToken(token);

        if (passwordResetToken != null) {

            final Calendar cal = Calendar.getInstance();
            if ((passwordResetToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
                userService.deletePasswordResetToken(passwordResetToken);
                modelAndView.setViewName("/passwordReset/expiredPasswordResetTokenError");

            } else {
                modelAndView.addObject("passwordResetDto", new PasswordResetDto());
                modelAndView.addObject("token", passwordResetToken.getToken());
                modelAndView.setViewName("/passwordReset/passwordResetConfirmForm");
            }
        } else {
            modelAndView.setViewName("/passwordReset/badPasswordResetTokenError");
        }

        return modelAndView;
    }

    //TODO - Validation of this new password, i.e. min 8 characters etc.
    @RequestMapping(value = "/confirm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processPasswordResetForm(@Valid PasswordResetDto passwordResetDto, BindingResult result,
                                                 ModelAndView modelAndView, String token) {

        if (result.hasErrors()) {
            modelAndView.addObject("token", token);
            modelAndView.setViewName("/passwordReset/passwordResetConfirmForm");
            return modelAndView;
        }

        userService.changePassword(token, passwordResetDto);
        userService.deletePasswordResetToken(userService.findPasswordResetToken(token));
        modelAndView.setViewName("/passwordReset/passwordChangedSuccessfully");
        return modelAndView;
    }


}
