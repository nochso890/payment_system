package com.task.payment_system.payment.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum ApprovalStatus {
    WAITING,
    APPROVED,
    REJECT
    ;

    @Converter
    public static class ApprovalStatusConverter implements AttributeConverter<ApprovalStatus, String>{

        @Override
        public String convertToDatabaseColumn(ApprovalStatus attribute) {
            return attribute.toString();
        }

        @Override
        public ApprovalStatus convertToEntityAttribute(String dbData) {
            return ApprovalStatus.valueOf(dbData);
        }
    }
}
