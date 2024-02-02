package com.task.payment_system.balance;

import com.task.payment_system.balance.entity.BalanceEntity;
import com.task.payment_system.balance.service.BalanceService;
import com.task.payment_system.balance.vo.BalanceResponse;
import com.task.payment_system.common.BaseResponse;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BalanceControllerTest {

    @Mock
    private BalanceService balanceService;

    @InjectMocks
    private BalanceController balanceController;

    @Test
    public void testGetBalance() {

    }
}

