package ru.korchinskiy.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.korchinskiy.enums.OrderStatus;
import ru.korchinskiy.jms.MessageSender;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.AdminOrderService;
import ru.korchinskiy.service.OrderService;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {
    private static Logger logger = Logger.getLogger(AdminOrderServiceImpl.class);

    private OrderService orderService;
    private MessageSender messageSender;

    @Override
    public Message updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        Message message = orderService.updateOrderStatus(orderId, orderStatus);
        if (orderStatus == OrderStatus.DELIVERED) {
            messageSender.sendMessage(Message.UPDATE);
            logger.info(Message.UPDATE);
        }
        return message;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }
}
