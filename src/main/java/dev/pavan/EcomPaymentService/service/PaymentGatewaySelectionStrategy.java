package dev.pavan.EcomPaymentService.service;

import org.springframework.context.annotation.Bean;

public interface PaymentGatewaySelectionStrategy {

    int PaymentGatewaySelection();
}
