package com.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DealRequest {

    @NotBlank(message = "Deal Unique Id cannot be blank")
    private String uniqueId;

    @NotBlank(message = "From Currency ISO Code cannot be blank")
    @Pattern(regexp = "[A-Z]{3}", message = "From Currency ISO Code must be a 3-letter uppercase ISO code")
    private String fromCurrencyIsoCode;

    @NotBlank(message = "To Currency ISO Code cannot be blank")
    @Pattern(regexp = "[A-Z]{3}", message = "To Currency ISO Code must be a 3-letter uppercase ISO code")
    private String toCurrencyIsoCode;

    @NotNull(message = "Deal Timestamp cannot be null")
    private LocalDateTime dealTimestamp;

    @NotNull(message = "Deal Amount cannot be null")
    private Double dealAmount;
}
