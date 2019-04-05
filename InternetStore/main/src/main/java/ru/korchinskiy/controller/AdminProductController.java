package ru.korchinskiy.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.NewProductDto;
import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.CategoryService;
import ru.korchinskiy.service.ProductService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {
    private static Logger logger = Logger.getLogger(AdminProductController.class);

    private ProductService productService;
    private CategoryService categoryService;

    @GetMapping
    public String productList(Model model) {
        List<ProductDto> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "adminProducts";
    }

    @GetMapping("/addForm")
    public String showForm(Model model) {
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("allCategories", categories);
        model.addAttribute("product", new NewProductDto());
        return "adminProductForm";
    }

    @PostMapping("/addProduct")
    public String addProduct(@Valid @ModelAttribute("product") NewProductDto productDto,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            logger.info(Message.VALIDATION_ADD_PRODUCT_FAIL);
            List<CategoryDto> categories = categoryService.getAllCategories();
            model.addAttribute("allCategories", categories);
            return "adminProductForm";
        }
        Message message = productService.saveProduct(productDto);
        List<ProductDto> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("message", message);
        return "adminProducts";
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
