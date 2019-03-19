package ru.korchinskiy.dao;

import ru.korchinskiy.entity.DeliveryType;

import java.util.List;

public interface DeliveryTypeDAO {
    DeliveryType getDeliveryTypeById(Long id);

    List<DeliveryType> getAllDeliveryTypes();
}
