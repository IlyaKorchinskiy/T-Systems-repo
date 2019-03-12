package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.korchinskiy.dto.ProductWithCategoriesDto;
import ru.korchinskiy.service.ProductService;

@Controller
@RequestMapping("/catalog/product")
public class ProductController {
    private ProductService productService;

    @GetMapping("/{id}")
    public String showProduct(@PathVariable("id") Long id, Model model) {
        ProductWithCategoriesDto product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product";
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
