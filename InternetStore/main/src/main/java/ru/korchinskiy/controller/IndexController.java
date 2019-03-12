package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.service.CategoryService;

import java.util.Set;

@Controller
@RequestMapping("/")
public class IndexController {
    private CategoryService categoryService;

    @GetMapping
    public String main(Model model) {
        Set<CategoryDto> mainCategories = categoryService.getCategoriesByParentId(0L);
        model.addAttribute("mainCategories", mainCategories);
        return "index";
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
