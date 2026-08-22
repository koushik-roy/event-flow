package com.koushik.eventflow.domain.service;

import com.koushik.eventflow.domain.dto.CreateOrderRequest;
import com.koushik.eventflow.eventStore.service.EventStoreService;
import com.koushik.eventflow.domain.aggregate.order.OrderAggregate;
import com.koushik.eventflow.domain.dto.OrderItem;
import com.koushik.eventflow.domain.event.OrderCreated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final EventStoreService eventStoreService;

    public OrderCreated createOrder(CreateOrderRequest request) {
        String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 5);
        BigDecimal totalAmount = calculateTotalAmount(request.items());

        OrderAggregate aggregate = new OrderAggregate();
        OrderCreated event = aggregate.create(orderId, request.customerId(), request.items(), totalAmount);

        eventStoreService.persist(event);
        return event;
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> items) {
        BigDecimal total = BigDecimal.valueOf(0.00);
        for (OrderItem i : items) {
            total = total.add(i.price().multiply(new BigDecimal(i.quantity())));
        }

        return total;
    }


}
