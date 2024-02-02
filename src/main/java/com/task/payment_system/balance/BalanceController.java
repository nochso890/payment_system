package com.task.payment_system.balance;

import com.task.payment_system.balance.service.BalanceService;
import com.task.payment_system.balance.vo.BalanceResponse;
import com.task.payment_system.common.base.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @GetMapping("/balance/{userId}")
    public BaseResponse<BalanceResponse> getBalance(@PathVariable("userId") String userId) {
        var balance = balanceService.getBalance(userId);
        return new BaseResponse<>(new BalanceResponse(balance));
    }
}
