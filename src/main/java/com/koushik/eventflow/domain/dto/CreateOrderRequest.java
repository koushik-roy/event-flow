package com.koushik.eventflow.domain.dto;

import java.util.List;

public record CreateOrderRequest(
        Long customerId,
        List<OrderItem> items
) {
}
