package dev.pavan.EcomPaymentService.service;

import com.razorpay.RazorpayException;
import dev.pavan.EcomPaymentService.dto.PaymentResponse;

public interface PaymentService {

    String doPayment(String email,
                              String phoneNumber,
                              Long amount,
                              String orderId) throws RazorpayException;
}
