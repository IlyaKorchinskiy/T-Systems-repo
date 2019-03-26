package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.korchinskiy.dto.UserDto;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private UserService userService;

    @GetMapping
    public String profile(Model model,
                          HttpSession session) {
        UserDto user = userService.getUserById(((UserDto) session.getAttribute("user")).getId());
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/editUserInfo")
    public String editUserInfo(@ModelAttribute("user") UserDto userDto,
                               Model model,
                               HttpSession session) {
        Message message = userService.updateUser(userDto, session);
        UserDto user = userService.getUserById(userDto.getId());
        model.addAttribute("infoMessage", message);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/addAddress")
    public String addAddress(Model model,
                             @RequestParam(name = "address") String address,
                             HttpSession session) {
        Message message = userService.addUserAddress(address, (UserDto) session.getAttribute("user"));
        UserDto user = userService.getUserById(((UserDto) session.getAttribute("user")).getId());
        model.addAttribute("addressMessage", message);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/deleteAddress")
    @ResponseBody
    public Message deleteAddress(@RequestParam(name = "addressId") Long addressId,
                                 HttpSession session) {
        return userService.deleteUserAddress(addressId, (UserDto) session.getAttribute("user"));
    }

    @PostMapping("/editAddress")
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
}
