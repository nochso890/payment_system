package com.task.payment_system.utils;

import com.task.payment_system.payment.enums.PaymentError;
import com.task.payment_system.payment.exception.PaymentException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {
    public static boolean isCardExpiryDated(String cardExpiryDate) throws ParseException{
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        var split = getStrExpireYear(cardExpiryDate).split("/");
        int expiryYear = Integer.parseInt(split[1]);
        int expiryMonth = Integer.parseInt(split[0]);

        return currentYear <= expiryYear && (currentMonth <= expiryMonth);
    }

    public static String getStrExpireYear(String cardExpiryDate){
        String[] parts = cardExpiryDate.split("/");
        if (parts.length == 2) {
            return parts[0]+"/"+getCentury() + parts[1];
        }else{
            throw new PaymentException(PaymentError.CREDIT_CARD_EXPIRED);
        }
    }

    public static String getCentury(){
        Calendar currentCalender = Calendar.getInstance();
        var year = currentCalender.get(Calendar.YEAR);
        return String.valueOf(year/100);
    }

}
