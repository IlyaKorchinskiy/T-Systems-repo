package ru.korchinskiy.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.CategoryTreeDto;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {
    private CategoryService categoryService;

    @GetMapping
    public String categoriesList(Model model) {
        List<CategoryTreeDto> categories = categoryService.getCategoriesWithSubcategories();
        List<CategoryDto> allCategories = categoryService.getAllCategories();
        model.addAttribute("categories", new Gson().toJson(categories));
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("category", new CategoryDto());
        return "adminCategories";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CategoryDto getCategory(@PathVariable("id") Long categoryId) {
        CategoryDto category = categoryService.getCategoryById(categoryId);
        return category;
    }

    @PostMapping("/edit")
    public String editCategory(@ModelAttribute("category") CategoryDto categoryDto,
                               Model model) {
        Message message = categoryService.updateCategory(categoryDto);
        List<CategoryTreeDto> categories = categoryService.getCategoriesWithSubcategories();
        List<CategoryDto> allCategories = categoryService.getAllCategories();
        model.addAttribute("categories", new Gson().toJson(categories));
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("category", new CategoryDto());
        model.addAttribute("message", message);
        return "adminCategories";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute(name = "category") CategoryDto categoryDto,
                              Model model) {
        Message message = categoryService.saveCategory(categoryDto);
        List<CategoryTreeDto> categories = categoryService.getCategoriesWithSubcategories();
        List<CategoryDto> allCategories = categoryService.getAllCategories();
        model.addAttribute("categories", new Gson().toJson(categories));
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("category", new CategoryDto());
        model.addAttribute("message", message);
        return "adminCategories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long categoryId,
                                 Model model) {
        Message message = categoryService.removeCategory(categoryId);
        List<CategoryTreeDto> categories = categoryService.getCategoriesWithSubcategories();
        List<CategoryDto> allCategories = categoryService.getAllCategories();
        model.addAttribute("categories", new Gson().toJson(categories));
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("category", new CategoryDto());
        model.addAttribute("message", message);
        return "adminCategories";
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
