package com.task.payment_system.balance.repository;

import com.task.payment_system.balance.entity.BalanceEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<BalanceEntity,Long> {

    Optional<BalanceEntity> findByUserId(String userId);

}
