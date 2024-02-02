package com.task.payment_system.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

    public static boolean isCardExpiryDated(String cardExpiryDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        Calendar currentCalender = Calendar.getInstance();
        Calendar expiryCalender = Calendar.getInstance();

        Date expiryDate = dateFormat.parse(cardExpiryDate);
        expiryCalender.setTime(expiryDate);

        return currentCalender.after(expiryCalender);

    }

}
