package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String showCatalog(@RequestParam(name = "id") Long id,
                              @RequestParam(name = "minCost", required = false) Double minCost,
                              @RequestParam(name = "maxCost", required = false) Double maxCost,
                              Model model) {
        CategoryDto category = categoryService.getCategoryById(id);
        Set<ProductDto> products = productService.getProductsByCategoryAndCost(id, minCost, maxCost);
        Set<CategoryDto> mainCategories = categoryService.getCategoriesByParentId(0L);
        Set<CategoryDto> subCategories = categoryService.getCategoriesByParentId(id);
        model.addAttribute("category", category);
        model.addAttribute("products", products);
        model.addAttribute("mainCategories", mainCategories);
        model.addAttribute("subCategories", subCategories);
        model.addAttribute("minCost", minCost);
        model.addAttribute("maxCost", maxCost);
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
