package com.koushik.eventflow.domain.controller;

import com.koushik.eventflow.domain.dto.PaymentRequest;
import com.koushik.eventflow.domain.event.PaymentCompleted;
import com.koushik.eventflow.domain.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentCompleted> completePayment(
            @RequestBody PaymentRequest paymentRequest
    ) {
        PaymentCompleted event = paymentService.completePayment(paymentRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(event);
    }
}
