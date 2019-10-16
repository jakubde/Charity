package pl.coderslab.charity.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.charity.model.dtos.RoleDto;
import pl.coderslab.charity.model.dtos.UserDto;
import pl.coderslab.charity.services.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String prepareAddUser(Model model){

        model.addAttribute("user", new UserDto());

        return "register";
    }

    @PostMapping
    public String processAddUser(UserDto userDto){
        userDto.setId(null);
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());//do serwisu
        userDto.setPassword(encodedPassword);//do serwisu
        userDto.setEnabled(true);
        RoleDto roleUser = new RoleDto();
        roleUser.setAuthority("ROLE_USER");
        userDto.getRoles().add(roleUser);

        userService.saveUser(userDto);

        return "redirect:/";
    }
}
