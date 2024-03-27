package dev.pavan.EcomPaymentService.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentGatewaySelectionStartegyimpl implements PaymentGatewaySelectionStrategy{
    @Override
    public int PaymentGatewaySelection() {
        return 0;
    }
}
