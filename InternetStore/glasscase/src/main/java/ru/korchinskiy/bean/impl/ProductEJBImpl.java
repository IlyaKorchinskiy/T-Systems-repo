package ru.korchinskiy.bean.impl;

import org.apache.log4j.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import ru.korchinskiy.dto.ProductStatsDto;
import ru.korchinskiy.bean.ProductEJB;

import javax.ejb.Stateless;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless(name = "productEJB")
public class ProductEJBImpl implements ProductEJB {
    private static final String PRODUCT_URL = "http://localhost:8189/store/products/top";
    private static Logger logger = Logger.getLogger(ProductEJBImpl.class);

    @Override
    public List<ProductStatsDto> getProductsTopList() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(PRODUCT_URL);
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();
        logger.info(response.getStatusInfo());
        if (response.getStatus() != 200) {
            return null;
        }
        return response.readEntity(new GenericType<List<ProductStatsDto>>(){});
    }
}
