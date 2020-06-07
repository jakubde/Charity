package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.dtos.UserDto;
import pl.coderslab.charity.services.UserService;


@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String usersList(Model model) {

        model.addAttribute("userDtos", userService.findAllUsers());
        return "admin/users/list";
    }

    @GetMapping("/edit")
    public String editableUsersList(Model model) {
        model.addAttribute("userDtos", userService.findAllUsers());
        return "admin/users/editableList";
    }

    @GetMapping("/add")
    public String prepareAddUser(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "admin/users/add";
    }

    @PostMapping("/add")
    public String processAddUser(Model model, UserDto userDto) {
        userService.createNewUser(userDto);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String prepareEditUser(@PathVariable String id, Model model) {
        model.addAttribute(userService.findUserById(Long.parseLong(id)));
        return "admin/users/edit";
    }

    @PostMapping("/edit/{id}")
    public String processEditUser(@PathVariable String id, UserDto userDto) {
        //to do - BindingResult, validation
        userService.updateUserDto(Long.parseLong(id), userDto);
        return "redirect:/users/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(Long.parseLong(id));
        return "redirect:/users/edit";
    }

    @GetMapping("/profile/{id}")
    public String showUserProfile(@PathVariable Long id, Model model) {
        model.addAttribute("userDto", userService.findUserById(id));
        return "/admin/users/profile";
    }

}
