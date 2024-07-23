package org.bobpark.study.domain.payment.model;

import static org.apache.commons.lang3.ObjectUtils.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
public class Payment {

    private final Long orderId;
    private final String currency;

    // 실수를 정확히 계산하려면 BigDecimal 를 사용해야한다.
    private final BigDecimal foreignCurrencyAmount;
    private final BigDecimal exRate;
    private final BigDecimal convertedAmount;
    private final LocalDateTime validUntil;

    @Builder
    private Payment(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exRate,
        BigDecimal convertedAmount, LocalDateTime validUntil) {
        this.orderId = orderId;
        this.currency = currency;
        this.foreignCurrencyAmount = defaultIfNull(foreignCurrencyAmount, BigDecimal.ZERO);
        this.exRate = defaultIfNull(exRate, BigDecimal.ZERO);
        this.convertedAmount = defaultIfNull(convertedAmount, BigDecimal.ZERO);
        this.validUntil = defaultIfNull(validUntil, LocalDateTime.now());
    }
}
