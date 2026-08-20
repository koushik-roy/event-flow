package com.koushik.eventflow.order.domain.aggregate;

import com.koushik.eventflow.order.domain.dto.OrderItem;
import com.koushik.eventflow.order.domain.event.OrderCreated;

import java.math.BigDecimal;
import java.util.List;

public class OrderAggregate {

    private String orderId;
    private Long customerId;
    private List<OrderItem> items;
    private BigDecimal totalAmount;
    private long version = 0;
    private OrderStatus status;

    public OrderCreated create(
            String orderId,
            Long customerId,
            List<OrderItem> items,
            BigDecimal totalAmount
    ) {
        if (this.version != 0) {
            throw new IllegalStateException("Order already exists: " + orderId);
        }
        long nextVersion = this.version + 1;
        OrderCreated event = new OrderCreated(
                orderId,
                nextVersion,
                customerId,
                items,
                totalAmount);
        apply(event);
        return event;
    }

    private void apply(OrderCreated event) {
        this.orderId = event.aggregateId();
        this.customerId = event.customerId();
        this.items = event.items();
        this.totalAmount = event.totalAmount();
        this.version = event.eventVersion();
        this.status = OrderStatus.CREATED;
    }
}
