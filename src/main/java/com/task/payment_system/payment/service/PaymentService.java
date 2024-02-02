package com.task.payment_system.payment.service;

import com.task.payment_system.balance.service.BalanceService;
import com.task.payment_system.commission.service.CommissionService;
import com.task.payment_system.payment.entity.CreditCardPaymentEntity;
import com.task.payment_system.payment.entity.PaymentEntity;
import com.task.payment_system.payment.enums.ApprovalStatus;
import com.task.payment_system.payment.enums.PaymentError;
import com.task.payment_system.payment.exception.PaymentException;
import com.task.payment_system.payment.repository.CreditCardPaymentRepository;
import com.task.payment_system.payment.repository.PaymentRepository;
import com.task.payment_system.payment.vo.ApprovalRequest;
import com.task.payment_system.payment.vo.EstimateRequest;
import com.task.payment_system.payment.vo.PaymentDetails;
import com.task.payment_system.utils.DateUtils;
import java.math.BigDecimal;
import java.text.ParseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final CreditCardPaymentRepository creditCardPaymentRepository;
    private final PaymentRepository paymentRepository;
    private final BalanceService balanceService;
    private final CommissionService commissionService;

    public CreditCardPaymentEntity searchPaymentByCardNumber(PaymentDetails details){
        return creditCardPaymentRepository.findByCardNumber(details.getCardNumber()).orElseThrow(() -> new PaymentException(
            PaymentError.NOT_FOUND_PAYMENT_DETAILS));
    }

    public void verifyBeforeApproval(String userId, String expiryDate, BigDecimal amount){
        verifyCreditCardExpiryDate(expiryDate);
        verifySufficientBalance(userId,amount);
    }

    public PaymentEntity approval(ApprovalRequest request){
        var creditCardPaymentEntity = searchPaymentByCardNumber(request.getPaymentDetails());
        var commission = commissionService.getCommission(request.getMerchantId());
        return paymentRepository.save(request.toEntity(creditCardPaymentEntity.getId(),commission.getId()));
    }

    private void verifySufficientBalance(String userId, BigDecimal amount){
        var balance = balanceService.getBalance(userId).getBalance();
        if(amount.compareTo(balance) > 0){
            throw new PaymentException(PaymentError.INSUFFICIENT_BALANCE);
        }
    }

    private void verifyCreditCardExpiryDate(String cardExpiryDate) {
        try{
            if(!DateUtils.isCardExpiryDated(cardExpiryDate))
                throw new PaymentException(PaymentError.CREDIT_CARD_EXPIRED);
        }catch (ParseException e){
            log.error("VerifyExpiryDateByCreditCard_ParserException", e);

        }
    }

}
