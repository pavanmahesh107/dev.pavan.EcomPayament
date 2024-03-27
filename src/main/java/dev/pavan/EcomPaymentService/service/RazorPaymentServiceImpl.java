package dev.pavan.EcomPaymentService.service;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import dev.pavan.EcomPaymentService.dto.PaymentResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("razorpay")
public class RazorPaymentServiceImpl implements PaymentService{

    private RazorpayClient razorpayClient;

    private RazorPaymentServiceImpl(RazorpayClient razorpayClient){
        this.razorpayClient = razorpayClient;
    }


    @Override
    public String doPayment(String email, String phoneNumber, Long amount, String orderId) throws RazorpayException {
        try {
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", amount);
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("receipt", orderId);

            JSONObject customer = new JSONObject();
            customer.put("email", email);
            customer.put("phone", phoneNumber);
            paymentLinkRequest.put("customer", customer);

            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);

            paymentLinkRequest.put("callback_url", "https://domian.com/razorpayWebHook");
            paymentLinkRequest.put("callback_method", "post");

            PaymentLink response = razorpayClient.paymentLink.create(paymentLinkRequest);
            return response.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
            return null;
    }
}
