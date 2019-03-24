package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {
    private ProductService productService;

    @GetMapping
    public String productList(Model model) {
        List<ProductDto> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "adminProducts";
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
