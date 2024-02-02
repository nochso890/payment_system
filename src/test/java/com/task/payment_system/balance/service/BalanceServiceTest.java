package com.task.payment_system.balance.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.task.payment_system.balance.entity.BalanceEntity;
import com.task.payment_system.balance.exception.BalanceException;
import com.task.payment_system.balance.repository.BalanceRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BalanceServiceTest {

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private BalanceService balanceService;

    @Test
    @DisplayName("존재하는 사용자 잔액 조회 기능테스트")
    void testGetBalance_ExistingUser() {
        String userId = "12345";
        BigDecimal expectedBalance = BigDecimal.valueOf(1000.00).setScale(2);
        BalanceEntity expectedEntity = BalanceEntity.builder().balance(expectedBalance).build();

        when(balanceRepository.findByUserId(userId)).thenReturn(Optional.of(expectedEntity));

        BalanceEntity result = balanceService.getBalance(userId);

        assertEquals(expectedEntity.getBalance(), result.getBalance());

        verify(balanceRepository, times(1)).findByUserId(userId);
    }

    @Test
    @DisplayName("존재하지 않는 사용자 잔액 조회 에러테스트")
    void testGetBalance_NonExistingUser() {
        String userId = "123";

        when(balanceRepository.findByUserId(userId)).thenReturn(Optional.empty());

        assertThrows(BalanceException.class, () -> balanceService.getBalance(userId));

        verify(balanceRepository, times(1)).findByUserId(userId);
    }
}
