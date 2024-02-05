package com.task.payment_system.commission.entity;

import com.task.payment_system.commission.enums.CommissionType;
import com.task.payment_system.commission.enums.CommissionType.CommissionTypeConverter;
import com.task.payment_system.common.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@SuperBuilder
@Entity(name = "commission_rate")
@NoArgsConstructor
@AllArgsConstructor
public class CommissionRateEntity extends BaseEntity {

    @Column(name = "merchant_id")
    private String merchantId;
    @Column(name = "commission_type")
    @Convert(converter = CommissionTypeConverter.class)
    private CommissionType commissionType;
    private BigDecimal amount;
    private BigDecimal rate;
    private boolean status;

}
