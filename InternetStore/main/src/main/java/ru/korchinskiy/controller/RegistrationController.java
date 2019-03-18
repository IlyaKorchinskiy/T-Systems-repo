package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.CategoryWithProductsDto;
import ru.korchinskiy.dto.UserDto;
import ru.korchinskiy.service.CategoryService;

import java.util.Set;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private CategoryService categoryService;

    @GetMapping
    public String showRegPage(Model model) {
        Set<CategoryDto> mainCategories = categoryService.getCategoriesByParentId(0L);
        model.addAttribute("user", new UserDto());
        model.addAttribute("mainCategories", mainCategories);
        return "registration";
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
