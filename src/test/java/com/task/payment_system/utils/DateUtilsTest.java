package com.task.payment_system.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.h2.mvstore.DataUtils;
import org.junit.jupiter.api.Test;

class DateUtilsTest {

    @Test
    void isCardExpiryDated() throws Exception{
        String expiryDate = "12/24";

        var cardExpiryDated = DateUtils.isCardExpiryDated(expiryDate);

        assertTrue(cardExpiryDated);
    }
}
