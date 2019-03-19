package ru.korchinskiy.dao;

import ru.korchinskiy.entity.PaymentType;

import java.util.List;

public interface PaymentTypeDAO {
    PaymentType getPaymentTypeById(Long id);

    List<PaymentType> getAllPaymentTypes();
}
