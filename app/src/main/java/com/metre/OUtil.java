package com.metre;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class OUtil {

    public static String formatarMoeda2(BigDecimal val) {
        DecimalFormat df = new DecimalFormat("#,###,##0.00");
        return df.format(val);
    }
}
