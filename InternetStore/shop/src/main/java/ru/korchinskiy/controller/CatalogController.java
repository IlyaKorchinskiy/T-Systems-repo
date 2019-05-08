package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.CategoryWithProductsDto;
import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.service.CategoryService;
import ru.korchinskiy.service.ProductService;
import ru.korchinskiy.service.impl.CategoryServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    private CategoryService categoryService;
    private ProductService productService;

    @GetMapping
    public String showCatalog(@RequestParam(name = "id") Long id,
                              @RequestParam(name = "minCost", required = false) Double minCost,
                              @RequestParam(name = "maxCost", required = false) Double maxCost,
                              @RequestParam(name = "year", required = false) String year,
                              Model model) {
        CategoryWithProductsDto category = categoryService.getCategoryWithProductsByParams(id, minCost, maxCost, year);
        List<CategoryDto> mainCategories = categoryService.getCategoriesByParentId(CategoryServiceImpl.ROOT_CATEGORY);
        List<CategoryDto> subCategories = categoryService.getCategoriesByParentId(id);
        List<Integer> years = productService.getProductYears();
        model.addAttribute("category", category);
        model.addAttribute("mainCategories", mainCategories);
        model.addAttribute("subCategories", subCategories);
        model.addAttribute("minCost", minCost);
        model.addAttribute("maxCost", maxCost);
        model.addAttribute("years", years);
        model.addAttribute("chosenYear", year);
        return "catalog";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(name = "search") String search,
                                 Model model) {
        List<CategoryDto> mainCategories = categoryService.getCategoriesByParentId(CategoryServiceImpl.ROOT_CATEGORY);
        List<ProductDto> products = productService.findProductsBySearch(search);
        model.addAttribute("mainCategories", mainCategories);
        model.addAttribute("products", products);
        model.addAttribute("search", search);
        return "catalog";
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
