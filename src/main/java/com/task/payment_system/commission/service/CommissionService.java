package com.task.payment_system.commission.service;

import com.task.payment_system.commission.entity.CommissionRateEntity;
import com.task.payment_system.commission.enums.CommissionError;
import com.task.payment_system.commission.exception.CommissionException;
import com.task.payment_system.commission.repository.CommissionRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommissionService {

    private final CommissionRateRepository commissionRateRepository;

    public CommissionRateEntity getCommission(String merchantId){
        return commissionRateRepository.findByMerchantId(merchantId).orElseThrow(() -> new CommissionException(
            CommissionError.NOT_FOUND_COMISSION_DATA));
    }

}
