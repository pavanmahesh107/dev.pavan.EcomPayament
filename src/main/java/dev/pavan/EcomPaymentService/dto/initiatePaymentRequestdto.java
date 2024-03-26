package dev.pavan.EcomPaymentService.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class initiatePaymentRequestdto {

    private String email;
    private String phoneNumber;
    private Long Amount;
    private String orderId;
}
