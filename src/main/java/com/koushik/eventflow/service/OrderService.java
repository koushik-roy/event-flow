package com.koushik.eventflow.service;

import com.koushik.eventflow.dto.CreateOrderRequest;
import com.koushik.eventflow.order.domain.aggregate.OrderAggregate;
import com.koushik.eventflow.order.domain.dto.OrderItem;
import com.koushik.eventflow.order.domain.event.OrderCreated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    public OrderCreated createOrder(CreateOrderRequest request) {
        String orderId = "ORD-" + UUID.randomUUID().toString();
        BigDecimal totalAmount = calculateTotalAmount(request.items());

        OrderAggregate aggregate = new OrderAggregate();
        OrderCreated event = aggregate.create(orderId, request.customerId(), request.items(), totalAmount);

        return event;


    }

    public BigDecimal calculateTotalAmount(List<OrderItem> items) {
        BigDecimal total = BigDecimal.valueOf(0.00);
        for (OrderItem i : items) {
            total = total.add(i.price().multiply(new BigDecimal(i.quantity())));
        }

        return total;
    }


}
