package org.bobpark.study.domain.payment.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.bobpark.study.domain.payment.model.ExRateData;
import org.bobpark.study.domain.payment.model.Payment;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentService {

    private final RestTemplate restTemplate;

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) {

        // https://open.er-api.com/v6/latest/USD
        RequestEntity<Void> requestEntity =
            RequestEntity.get(String.format("https://open.er-api.com/v6/latest/%s", currency))
                .build();

        ResponseEntity<ExRateData> response = restTemplate.exchange(requestEntity, ExRateData.class);
        ExRateData exRateData = response.getBody();

        BigDecimal exRate = exRateData.rates().get("KRW");

        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);

        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return Payment.builder()
            .orderId(orderId)
            .currency(currency)
            .foreignCurrencyAmount(foreignCurrencyAmount)
            .exRate(exRate)
            .convertedAmount(convertedAmount)
            .validUntil(validUntil)
            .build();
    }

}
