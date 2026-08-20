package com.koushik.eventflow.order.domain.controller;

import com.koushik.eventflow.dto.CreateOrderRequest;
import com.koushik.eventflow.order.domain.event.OrderCreated;
import com.koushik.eventflow.order.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderCreated> createOrder(
            @RequestBody CreateOrderRequest request
    ) {
        OrderCreated event = orderService.createOrder(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(event);
    }
}
