package ru.korchinskiy.dao;

import ru.korchinskiy.entity.PaymentType;

import java.util.List;

public interface PaymentTypeDAO {
    List<PaymentType> getAllPaymentTypes();
}
