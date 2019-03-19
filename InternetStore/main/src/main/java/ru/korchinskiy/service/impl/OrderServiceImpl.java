package ru.korchinskiy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.dao.*;
import ru.korchinskiy.dto.CartProductDto;
import ru.korchinskiy.dto.OrderDto;
import ru.korchinskiy.entity.Order;
import ru.korchinskiy.entity.Product;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.DTOMappingService;
import ru.korchinskiy.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    public static final String NOT_ENOUGH_PRODUCT = "Недостаточное количество товара";

    private ProductDAO productDAO;
    private OrderDAO orderDAO;
    private DTOMappingService dtoMappingService;
    private PaymentTypeDAO paymentTypeDAO;

    @Override
    @Transactional
    public Message saveOrder(OrderDto orderDto, List<CartProductDto> cartProducts, String sessionId) {
        Message message = new Message();
        List<Product> products = new ArrayList<>();
//        check for amounts
        for (CartProductDto cartProductDto : cartProducts) {
            Product product = productDAO.getProductForUpdate(cartProductDto.getProduct().getId());
            products.add(product);
            if (cartProductDto.getAmount() > product.getAmount()) {
                message.getErrors().add(NOT_ENOUGH_PRODUCT + " " + cartProductDto.getProduct().getTitle());
                return message;
            }
        }
//        reduce product amounts
        for (int i = 0; i < products.size(); i++) {
            int productAmount = products.get(0).getAmount();
            products.get(0).setAmount(productAmount - cartProducts.get(0).getAmount());
        }
        Order order = new Order();
        order.setPaymentType(paymentTypeDAO.getPaymentTypeById(orderDto.getDeliveryTypeId()));
        order.setDeliveryType();
        return null;
    }

    @Autowired
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Autowired
    public void setDtoMappingService(DTOMappingService dtoMappingService) {
        this.dtoMappingService = dtoMappingService;
    }

    @Autowired
    public void setPaymentTypeDAO(PaymentTypeDAO paymentTypeDAO) {
        this.paymentTypeDAO = paymentTypeDAO;
    }
}
