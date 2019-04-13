package ru.korchinskiy.ejb.impl;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.ejb.ProductEJB;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ProductEJBImpl implements ProductEJB {

    private List<ProductDto> productDtoList;

    @PostConstruct
    public void init() {
        productDtoList = updateProductsTop();
    }

    private List<ProductDto> updateProductsTop() {
        ResteasyClient client = new ResteasyClientBuilder().build();

    }

    @Override
    public List<ProductDto> getTopProducts() {
        return productDtoList;
    }
}
