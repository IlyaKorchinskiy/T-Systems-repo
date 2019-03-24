package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.UserDto;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.CategoryService;
import ru.korchinskiy.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    private CategoryService categoryService;
    private UserService userService;

    @GetMapping
    public String main(Model model) {
        List<CategoryDto> mainCategories = categoryService.getCategoriesByParentId(0L);
        model.addAttribute("mainCategories", mainCategories);
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String showRegPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute("user") UserDto user,
                          Model model) {
        Message message = userService.addUser(user);
        model.addAttribute("message", message);
        model.addAttribute("user", user);
        return "registration";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

}
