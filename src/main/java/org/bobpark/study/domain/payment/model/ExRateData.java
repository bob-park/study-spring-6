package org.bobpark.study.domain.payment.model;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExRateData(@JsonProperty("base_code") String baseCode,
                         @JsonProperty("rates") Map<String, BigDecimal> rates) {

}
