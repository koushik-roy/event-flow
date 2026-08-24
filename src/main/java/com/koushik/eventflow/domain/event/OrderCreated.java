package com.koushik.eventflow.domain.event;

import com.koushik.eventflow.domain.dto.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static com.koushik.eventflow.util.Constants.ORDER;

public record OrderCreated(
        UUID eventId,
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
