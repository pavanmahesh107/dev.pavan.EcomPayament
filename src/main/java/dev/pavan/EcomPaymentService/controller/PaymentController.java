package dev.pavan.EcomPaymentService.controller;


import dev.pavan.EcomPaymentService.dto.PaymentResponse;
import dev.pavan.EcomPaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

        @Autowired
        private PaymentService paymentService;

}
