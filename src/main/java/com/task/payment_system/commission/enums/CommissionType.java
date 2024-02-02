package com.task.payment_system.commission.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum CommissionType {
    FIXED,
    PERCENTAGE
    ;

    @Converter
    public static class CommissionTypeConverter implements AttributeConverter<CommissionType, String> {

        @Override
        public String convertToDatabaseColumn(CommissionType attribute) {
            return attribute.toString();
        }

        @Override
        public CommissionType convertToEntityAttribute(String dbData) {
            return CommissionType.valueOf(dbData);
        }
    }
}
