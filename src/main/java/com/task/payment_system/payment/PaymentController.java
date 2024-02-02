package com.task.payment_system.payment;

import com.task.payment_system.commission.service.CommissionService;
import com.task.payment_system.common.base.BaseResponse;
import com.task.payment_system.payment.service.PaymentService;
import com.task.payment_system.payment.vo.ApprovalRequest;
import com.task.payment_system.payment.vo.ApprovalResponse;
import com.task.payment_system.payment.vo.EstimateRequest;
import com.task.payment_system.payment.vo.EstimateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final CommissionService commissionService;

    @PostMapping("/estimate")
    public BaseResponse<EstimateResponse> getEstimate(@Valid @RequestBody EstimateRequest request) {
        var commissionRateEntity = commissionService.getCommission(request.getDestination());
        return new BaseResponse<>(new EstimateResponse(request, commissionRateEntity.getAmount()));
    }

    @PostMapping("/approval")
    public BaseResponse<ApprovalResponse> approval(@Valid @RequestBody ApprovalRequest request) {
        paymentService.verifyBeforeApproval(request.getUserId(), request.getPaymentDetails().getExpiryDate(),
            request.getAmount());
        var approvalEntity = paymentService.approval(request);
        return new BaseResponse<>(new ApprovalResponse(approvalEntity));
    }
}
