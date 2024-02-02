package com.task.payment_system.payment.repository;

import com.task.payment_system.payment.entity.CreditCardPaymentEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardPaymentRepository extends JpaRepository<CreditCardPaymentEntity,Long> {

    Optional<CreditCardPaymentEntity> findByCardNumber(String cardNumber);
}
