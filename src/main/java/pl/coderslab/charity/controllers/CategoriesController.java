package pl.coderslab.charity.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.dtos.CategoryDto;
import pl.coderslab.charity.model.entities.Category;
import pl.coderslab.charity.services.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

    private final CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String prepareListOfCategories(Model model) {
        model.addAttribute("categoryDtoList", categoryService.getList());
        return "/admin/categories/list";
    }

    @GetMapping("/edit")
    public String prepareEditableListOfCategories(Model model) {
        model.addAttribute("categoryDtoList", categoryService.getList());
        return "/admin/categories/editableList";
    }

    @GetMapping("/add")
    public String prepareAddCategory(Model model) {
        model.addAttribute("categoryDto", new CategoryDto());
        return "/admin/categories/add";
    }

    @PostMapping("/add")
    public String processAddCategory(CategoryDto categoryDto) {
        categoryService.saveCategory(categoryDto);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String prepareEditCategory(@PathVariable String id, Model model) {
        model.addAttribute("categoryDto", categoryService.findById(Long.parseLong(id)));
        return "/admin/categories/edit";
    }

    @PostMapping("/edit/{id}")
    public String processEditCategory(CategoryDto categoryDto) {
        categoryService.updateCategory(categoryDto);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id, Model model) {
        try {
            categoryService.deleteCategory(id);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("dataViolationFlag", "error");
            model.addAttribute("categoryDtoList", categoryService.getList());
            return "/admin/categories/editableList";
        }
        return "redirect:/categories";
    }

}
