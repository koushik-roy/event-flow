package com.koushik.eventflow.domain.aggregate.payment;

import com.koushik.eventflow.domain.event.PaymentCompleted;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentAggregate {
    private String paymentId;
    private String orderId;
    private BigDecimal amount;
    private long version = 0;
    private PaymentStatus status;

    public PaymentCompleted completePayment (
            String paymentId,
            String orderId,
            BigDecimal amount
    ) {
        if (this.version != 0) {
            throw new IllegalStateException("Payment already exists: " + this.paymentId);
        }
        long nextVersion = this.version + 1;
        PaymentCompleted event = new PaymentCompleted(
                UUID.randomUUID(),
                paymentId,
                nextVersion,
                orderId,
                amount
        );
        apply(event);
        return event;
    }

    private void apply(PaymentCompleted event) {
        this.paymentId = event.aggregateId();
        this.orderId = event.orderId();
        this.amount = event.amount();
        this.version = event.eventVersion();
        this.status = PaymentStatus.COMPLETED;
    }

}
