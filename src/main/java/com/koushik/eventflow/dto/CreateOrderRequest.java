package com.koushik.eventflow.dto;

import com.koushik.eventflow.order.domain.dto.OrderItem;

import java.util.List;

public record CreateOrderRequest(
        Long customerId,
        List<OrderItem> items
) {
}
