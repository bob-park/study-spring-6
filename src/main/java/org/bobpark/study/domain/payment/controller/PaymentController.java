package org.bobpark.study.domain.payment.controller;

import java.math.BigDecimal;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.bobpark.study.domain.payment.model.Payment;
import org.bobpark.study.domain.payment.service.PaymentService;

@RequiredArgsConstructor
@RestController
@RequestMapping("payment")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping(path = "prepare")
    public Payment prepare(@RequestParam long orderId, @RequestParam String currency,
        @RequestParam BigDecimal foreignCurrencyAmount) {
        return paymentService.prepare(orderId, currency, foreignCurrencyAmount);
    }

}
