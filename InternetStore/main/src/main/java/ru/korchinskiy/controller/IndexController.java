package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.UserDto;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.CategoryService;
import ru.korchinskiy.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
@RequestMapping("/")
public class IndexController {
    private CategoryService categoryService;
    private UserService userService;

    @GetMapping
    public String main(Model model) {
        Set<CategoryDto> mainCategories = categoryService.getCategoriesByParentId(0L);
        model.addAttribute("mainCategories", mainCategories);
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String showRegPage(Model model) {
        Set<CategoryDto> mainCategories = categoryService.getCategoriesByParentId(0L);
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

    @GetMapping("/profile")
    public String profile(Model model,
                          HttpSession session) {
        UserDto user = userService.getUserById(((UserDto) session.getAttribute("user")).getId());
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/addAddress")
    public String addAddress(Model model,
                             @RequestParam(name = "address") String address,
                             HttpSession session) {
        UserDto user = userService.addUserAddress(address, (UserDto) session.getAttribute("user"));
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/deleteAddress")
    @ResponseBody
    public Message deleteAddress(@RequestParam(name = "addressId") Long addressId,
                                 HttpSession session) {
        return userService.deleteUserAddress(addressId, (UserDto) session.getAttribute("user"));
    }

    @PostMapping("/profile/editAddress")
    @ResponseBody
    public Message editAddress(@RequestParam(name = "addressId") Long addressId,
                               @RequestParam(name = "address") String address,
                               HttpSession session) {
        return userService.updateUserAddress(addressId, address, (UserDto) session.getAttribute("user"));
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
