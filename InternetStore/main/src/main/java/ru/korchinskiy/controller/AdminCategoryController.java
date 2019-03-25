package ru.korchinskiy.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.CategoryWithSubcategoriesDto;
import ru.korchinskiy.service.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {
    private CategoryService categoryService;

    @GetMapping
    public String categoriesList(Model model) {
        List<CategoryWithSubcategoriesDto> categories = categoryService.getCategoriesWithSubcategories();
        List<CategoryDto> allCategories = categoryService.getAllCategories();
        model.addAttribute("categories", new Gson().toJson(categories));
        model.addAttribute("allCategories", allCategories);
        return "adminCategories";
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
