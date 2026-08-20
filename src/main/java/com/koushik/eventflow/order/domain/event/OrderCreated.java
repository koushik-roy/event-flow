package com.koushik.eventflow.order.domain.event;

import com.koushik.eventflow.order.domain.dto.OrderItem;
import com.koushik.eventflow.util.Constants;

import java.math.BigDecimal;
import java.util.List;

import static com.koushik.eventflow.util.Constants.ORDER;

public record OrderCreated(
        String aggregateId,
        long eventVersion,

        Long customerId,
        List<OrderItem> items,
        BigDecimal totalAmount
) implements DomainEvent {

    @Override
    public String aggregateType() {
        return ORDER;
    }
}
