package ru.korchinskiy.dao;

import ru.korchinskiy.entity.PaymentStatus;

public interface PaymentStatusDAO {
    PaymentStatus getPaymentStatusById(Long id);
}
