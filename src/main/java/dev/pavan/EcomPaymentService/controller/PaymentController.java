package dev.pavan.EcomPaymentService.controller;


import com.razorpay.RazorpayException;
import dev.pavan.EcomPaymentService.dto.PaymentResponse;
import dev.pavan.EcomPaymentService.dto.initiatePaymentRequestdto;
import dev.pavan.EcomPaymentService.service.PaymentGatewaySelectionStrategy;
import dev.pavan.EcomPaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

        //@Autowired field injection
        private PaymentService razorpayPaymentService;
        private PaymentService stripepayPaymentService;
        private PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy;
        public PaymentController(
                @Qualifier("razorpay") PaymentService razorpayPaymentService,
                @Qualifier("stripe") PaymentService stripepayPaymentService,
                PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy) {
                this.razorpayPaymentService = razorpayPaymentService;
                this.stripepayPaymentService = stripepayPaymentService;
                this.paymentGatewaySelectionStrategy = paymentGatewaySelectionStrategy;
        }

        @PostMapping("/payment")
        public String intiatePayment(@RequestBody initiatePaymentRequestdto requestdto) throws RazorpayException {
                int paymentGatewayOption = choosePaymentGateway();
                switch (paymentGatewayOption){
                        case 1: return razorpayPaymentService.doPayment(
                                requestdto.getEmail(),
                                requestdto.getPhoneNumber(),
                                requestdto.getAmount(),
                                requestdto.getOrderId()
                        );
                        case 2: return stripepayPaymentService.doPayment(
                                requestdto.getEmail(),
                                requestdto.getPhoneNumber(),
                                requestdto.getAmount(),
                                requestdto.getOrderId()
                        );

                }
                return null;
        }

        private int choosePaymentGateway(){
                return paymentGatewaySelectionStrategy.PaymentGatewaySelection();
        }
}
