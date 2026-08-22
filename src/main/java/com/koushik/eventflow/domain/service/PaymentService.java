package com.koushik.eventflow.domain.service;

import com.koushik.eventflow.domain.aggregate.payment.PaymentAggregate;
import com.koushik.eventflow.domain.dto.PaymentRequest;
import com.koushik.eventflow.domain.event.PaymentCompleted;
import com.koushik.eventflow.eventStore.service.EventStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final EventStoreService eventStoreService;

    public PaymentCompleted completePayment(PaymentRequest paymentRequest) {
        String paymentId = "PMT-" + UUID.randomUUID().toString().substring(0, 5);

        PaymentAggregate paymentAggregate = new PaymentAggregate();
        PaymentCompleted event = paymentAggregate.completePayment(paymentId,
                paymentRequest.orderId(),
                paymentRequest.amount());

        eventStoreService.persist(event);

        return event;
    }
}
