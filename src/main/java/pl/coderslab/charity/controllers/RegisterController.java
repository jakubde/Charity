package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import pl.coderslab.charity.model.dtos.UserDto;
import pl.coderslab.charity.services.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String prepareAddUser(Model model){

        model.addAttribute("user", new UserDto());

        return "register";
    }

    @PostMapping
    public String processAddUser(UserDto userDto){

        userService.saveUser(userDto);

        return "redirect:/";
    }
}
