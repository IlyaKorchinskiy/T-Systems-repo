package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.CategoryWithProductsDto;
import ru.korchinskiy.service.CategoryService;
import ru.korchinskiy.service.impl.CategoryServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    private CategoryService categoryService;

    @GetMapping
    public String showCatalog(@RequestParam(name = "id") Long id,
                              @RequestParam(name = "minCost", required = false) Double minCost,
                              @RequestParam(name = "maxCost", required = false) Double maxCost,
                              Model model) {
        CategoryWithProductsDto category = categoryService.getCategoryWithProductsByCost(id, minCost, maxCost);
        List<CategoryDto> mainCategories = categoryService.getCategoriesByParentId(CategoryServiceImpl.ROOT_CATEGORY);
        List<CategoryDto> subCategories = categoryService.getCategoriesByParentId(id);
        model.addAttribute("category", category);
        model.addAttribute("mainCategories", mainCategories);
        model.addAttribute("subCategories", subCategories);
        model.addAttribute("minCost", minCost);
        model.addAttribute("maxCost", maxCost);
        return "catalog";
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

}
