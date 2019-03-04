package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.service.CategoryService;
import ru.korchinskiy.service.ProductService;

import java.util.Set;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    private ProductService productService;
    private CategoryService categoryService;

    @GetMapping
    public String showCatalog(Model model) {
        CategoryDto category = categoryService.getCategoryById(1L);
        model.addAttribute("category", category);
        return "catalog";
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
