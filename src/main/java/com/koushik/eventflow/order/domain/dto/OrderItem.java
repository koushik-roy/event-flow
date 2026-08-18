package com.koushik.eventflow.order.domain.dto;

import java.math.BigDecimal;

public record OrderItem(
        Long productId,
        int quantity,
        BigDecimal price
) {
}
