package com.browser.wallet;

import java.math.BigDecimal;

public class PrecisionUtils {
    // 精度位数转换成精度，比如8转换成 100,000,000
    public static BigDecimal decimalsToPrecision(int decimals) {
        return new BigDecimal(10).pow(decimals);
    }
}
