package com.koushik.eventflow.domain.dto;

import java.math.BigDecimal;

public record PaymentRequest(
        String orderId,
        BigDecimal amount
) {
}
