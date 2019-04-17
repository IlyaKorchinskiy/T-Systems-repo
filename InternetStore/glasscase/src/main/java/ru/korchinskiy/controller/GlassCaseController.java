package ru.korchinskiy.controller;

import ru.korchinskiy.dto.ProductStatsDto;
import ru.korchinskiy.bean.ProductEJB;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.List;

@Named
@ApplicationScoped
public class GlassCaseController {
    @EJB
    private ProductEJB productEJB;

    public List<ProductStatsDto> getProductsTopList() {
        return productEJB.getProductsTopList();
    }
}
