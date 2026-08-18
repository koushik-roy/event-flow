package com.koushik.eventflow.order.domain.event;

import com.koushik.eventflow.order.domain.dto.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public record OrderCreated(
        String orderId,
        Long customerId,
        List<OrderItem> items,
        BigDecimal totalAmount
) {
}
