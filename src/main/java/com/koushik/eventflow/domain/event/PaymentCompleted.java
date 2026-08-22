package com.koushik.eventflow.domain.event;

import java.math.BigDecimal;

import static com.koushik.eventflow.util.Constants.PAYMENT;

public record PaymentCompleted(
        String aggregateId,
        long eventVersion,
        String orderId,
        BigDecimal amount

) implements DomainEvent{

    @Override
    public String aggregateType(){
        return PAYMENT;
    }
}
