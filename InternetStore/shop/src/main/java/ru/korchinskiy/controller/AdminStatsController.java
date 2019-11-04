package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.korchinskiy.dto.IndicatorDto;
import ru.korchinskiy.dto.ProductStatsDto;
import ru.korchinskiy.dto.UserStatsDto;
import ru.korchinskiy.service.StatsService;

import java.time.Month;
import java.util.List;

@Controller
@RequestMapping("/admin/stats")
public class AdminStatsController {
    private StatsService statsService;

    @GetMapping
    public String showStats(Model model) {
        List<ProductStatsDto> productStatsList = statsService.getTopTenProducts(null, null);
        List<UserStatsDto> userStatsList = statsService.getTopTenUsers(null, null);
        List<IndicatorDto> indicators = statsService.getMainStats(null, null);
        Month[] months = Month.values();
        Integer[] years = {2017, 2018, 2019};
        model.addAttribute("productStatsList", productStatsList);
        model.addAttribute("userStatsList", userStatsList);
        model.addAttribute("indicators", indicators);
        model.addAttribute("months", months);
        model.addAttribute("years", years);
        return "adminStats";
    }

    @GetMapping("/products")
    @ResponseBody
    public List<ProductStatsDto> getProductStatsList(@RequestParam(name = "month") Integer month,
                                                     @RequestParam(name = "year") Integer year) {
        return statsService.getTopTenProducts(month, year);
    }

    @GetMapping("/users")
    @ResponseBody
    public List<UserStatsDto> getUserStatsList(@RequestParam(name = "month") Integer month,
                                               @RequestParam(name = "year") Integer year) {
        return statsService.getTopTenUsers(month, year);
    }

    @GetMapping("/total")
    @ResponseBody
    public List<IndicatorDto> getMainStats(@RequestParam(name = "month") Integer month,
                                           @RequestParam(name = "year") Integer year) {
        return statsService.getMainStats(month, year);
    }

    @Autowired
    public void setStatsService(StatsService statsService) {
        this.statsService = statsService;
    }
}
