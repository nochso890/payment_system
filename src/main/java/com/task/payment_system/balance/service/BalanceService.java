package com.task.payment_system.balance.service;

import com.task.payment_system.balance.entity.BalanceEntity;
import com.task.payment_system.balance.enums.BalanceError;
import com.task.payment_system.balance.exception.BalanceException;
import com.task.payment_system.balance.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;

    public BalanceEntity getBalance(String userId) {
        return balanceRepository.findByUserId(userId)
            .orElseThrow(() -> new BalanceException(BalanceError.USER_BALANCE_NOT_FOUND));
    }

}
