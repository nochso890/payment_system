package com.task.payment_system.commission.repository;

import com.task.payment_system.commission.entity.CommissionRateEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommissionRateRepository extends JpaRepository<CommissionRateEntity,Long> {

    Optional<CommissionRateEntity> findByMerchantId(String merchantId);

}
