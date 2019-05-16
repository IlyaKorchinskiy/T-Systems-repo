package ru.korchinskiy.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.korchinskiy.dto.*;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.CategoryService;
import ru.korchinskiy.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
        return "adminProductList";
    }

    @GetMapping("/addForm")
    public String showForm(Model model) {
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("allCategories", categories);
        model.addAttribute("product", new NewProductDto());
        return "adminProductForm";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("product") NewProductDto productDto,
                             BindingResult result,
                             HttpServletRequest request,
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
        return "adminProductList";
    }

    @GetMapping("/{id}")
    public String showProduct(@PathVariable("id") Long id,
                              Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("photo", new PhotoDto());
        model.addAttribute("allCategories", categoryService.getAllCategories());
        return "adminProduct";
    }

    @PostMapping("/{id}/updateTitle")
    @ResponseBody
    public Message updateTitle(@RequestBody @Valid TitleDto titleDto,
                               BindingResult result,
                               @PathVariable("id") Long productId) {
        if (result.hasErrors()) {
            Message message = new Message();
            message.getErrors().add(Message.PRODUCT_UPDATE_TITLE_FAIL);
            logger.info(Message.PRODUCT_UPDATE_TITLE_FAIL);
            return message;
        }
        return productService.updateProductTitle(titleDto.getTitle(), productId);
    }

    @PostMapping("/{id}/updateAuthor")
    @ResponseBody
    public Message updateAuthor(@RequestBody @Valid AuthorDto authorDto,
                                BindingResult result,
                                @PathVariable("id") Long productId) {
        if (result.hasErrors()) {
            Message message = new Message();
            message.getErrors().add(Message.PRODUCT_UPDATE_AUTHOR_FAIL);
            logger.info(Message.PRODUCT_UPDATE_AUTHOR_FAIL);
            return message;
        }
        return productService.updateProductAuthor(authorDto.getAuthor(), productId);
    }

    @PostMapping("/{id}/updateYear")
    @ResponseBody
    public Message updateYear(@RequestBody @Valid YearDto yearDto,
                              BindingResult result,
                              @PathVariable("id") Long productId) {
        if (result.hasErrors()) {
            Message message = new Message();
            message.getErrors().add(Message.PRODUCT_UPDATE_YEAR_FAIL);
            logger.info(Message.PRODUCT_UPDATE_YEAR_FAIL);
            return message;
        }
        return productService.updateProductYear(yearDto.getYear(), productId);
    }

    @PostMapping("/{id}/updateDescription")
    @ResponseBody
    public Message updateDescription(@RequestBody @Valid DescriptionDto descriptionDto,
                                     BindingResult result,
                                     @PathVariable("id") Long productId) {
        if (result.hasErrors()) {
            Message message = new Message();
            message.getErrors().add(Message.PRODUCT_UPDATE_DESCRIPTION_FAIL);
            logger.info(Message.PRODUCT_UPDATE_DESCRIPTION_FAIL);
            return message;
        }
        return productService.updateProductDescription(descriptionDto.getDescription(), productId);
    }

    @PostMapping("/{id}/updateCategories")
    @ResponseBody
    public Message updateProductCategories(@RequestBody CategoryListDto categoryListDto,
                                           BindingResult result,
                                           @PathVariable("id") Long productId) {
        if (result.hasErrors()) {
            Message message = new Message();
            message.getErrors().add(Message.PRODUCT_UPDATE_CATEGORIES_FAIL);
            logger.info(Message.PRODUCT_UPDATE_CATEGORIES_FAIL);
            return message;
        }
        return productService.updateProductCategories(categoryListDto.getCategories(), productId);
    }

    @PostMapping("/{id}/updatePhotoMd")
    public String updateProductPhotoMd(@Valid @ModelAttribute("photo") PhotoDto photoMd,
                                       BindingResult result,
                                       @PathVariable("id") Long productId,
                                       Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        model.addAttribute("allCategories", categoryService.getAllCategories());
        if (result.hasErrors()) {
            logger.info(Message.PRODUCT_UPDATE_PHOTO_FAIL);
            return "adminProduct";
        }
        model.addAttribute("message", productService.updateProductPhotoMd(photoMd.getPhoto(), productId));
        return "adminProduct";
    }

    @PostMapping("/{id}/updatePhotoSm")
    public String updateProductPhotoSm(@Valid @ModelAttribute("photo") PhotoDto photoSm,
                                       BindingResult result,
                                       @PathVariable("id") Long productId,
                                       Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        model.addAttribute("allCategories", categoryService.getAllCategories());
        if (result.hasErrors()) {
            logger.info(Message.PRODUCT_UPDATE_PHOTO_FAIL);
            return "adminProduct";
        }
        model.addAttribute("message", productService.updateProductPhotoSm(photoSm.getPhoto(), productId));
        return "adminProduct";
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
