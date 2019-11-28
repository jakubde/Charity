package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.dtos.UserDto;
import pl.coderslab.charity.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admins")
public class AdminsController {

    private final UserService userService;

    public AdminsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String adminsList(Model model){
        List<UserDto> adminDtos = userService.findAllAdmins();
        model.addAttribute("adminDtos", adminDtos);
        return "admin/admins/list";
    }

    @GetMapping("/edit")
    public String editableAdminList(Model model){
        List<UserDto> adminDtos = userService.findAllAdmins();
        model.addAttribute("adminDtos", adminDtos);
        return "admin/admins/editableList";
    }

    @GetMapping("/add")
    public String prepareAddAdmin(Model model){
        model.addAttribute("admin", new UserDto());
        return "admin/admins/add";
    }

    @PostMapping("/add")
    public String processAddAdmin(UserDto adminDto){
        userService.createNewAdmin(adminDto);
        return "redirect:/admins";
    }

    @GetMapping("/edit/{id}")
    public String prepareEditAdmin(@PathVariable String id, Model model){
        model.addAttribute("admin", userService.findUserById(Long.parseLong(id)));
        return "admin/admins/edit";
    }

    @PostMapping("/edit/{id}")
    public String processEditAdmin(UserDto userDto, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/admins/edit";
        }
        if(userDto.getId() != null){
            userService.updateUserDto(userDto.getId(),userDto);
        }
        return "redirect:/admins/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable String id){
        userService.deleteUser(Long.parseLong(id));
        return "redirect:/admins/edit";
    }
}
