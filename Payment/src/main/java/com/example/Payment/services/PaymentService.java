package com.example.Payment.services;

import com.example.Payment.entity.Payment;
import com.example.Payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;


    public Payment doPayment(Payment payment){
        payment.setPaymentStatus(new Random().nextBoolean()?"success":"failure"); // randomly it will generate response either SUCCESS or FAILURE
        payment.setTransactionId(UUID.randomUUID().toString());
        return paymentRepository.save(payment);
    }

//    public String paymentProcessing() {
//        // api should be 3 partyy like razorpay or stripe../paytm/amazon pay
//        return new Random().nextBoolean()?"success":"failure";
//    }
}
