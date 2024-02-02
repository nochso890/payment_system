package com.task.payment_system.balance.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.task.payment_system.balance.entity.BalanceEntity;
import com.task.payment_system.balance.repository.BalanceRepository;
import java.math.BigDecimal;
import java.rmi.server.ExportException;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class BalanceServiceTest {

    @Autowired
    private BalanceRepository balanceRepository;

    @InjectMocks
    private BalanceService balanceService;

    @BeforeClass
    @Sql({"classpath:db/h2/schema.sql" , "classpath:db/h2/data_init.sql"})
    public static void setUpBeforeClass() throws Exception{
        System.out.println("TestDate Create and Insert");
    }

    @Test
    void testGetBalance() {
        String userId = "12345";
        BigDecimal initBalance = BigDecimal.valueOf(1000.00);

        BalanceEntity balanceEntity = balanceService.getBalance(userId);

        assertThat(balanceEntity).isNotNull();
        assertThat(balanceEntity.getBalance()).isEqualByComparingTo(initBalance);

    }
}
