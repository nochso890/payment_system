package com.task.payment_system.payment.repository;

import com.task.payment_system.payment.entity.PaymentEntity;
import com.task.payment_system.payment.enums.ApprovalStatus;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {

}
