package com.example.Order.services;

import com.example.Order.common.Payment;
import com.example.Order.common.TransactionRequest;
import com.example.Order.common.TransactionResponse;
import com.example.Order.entity.Order;
import com.example.Order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;


    public TransactionResponse saveOrder(TransactionRequest transactionRequest){

        String response;
        Order order = transactionRequest.getOrder();
        Payment payment = transactionRequest.getPayment();

        orderRepository.save(order);//persisting in Db to get autogenrated order id
        payment.setOrderId(order.getOrderId());
        payment.setAmount((order.getPrice()*order.getQty()));

        // do a rest call to payment microservice and pass order id
        Payment paymentResponse = restTemplate.postForObject(
//                "http://localhost:8082/payment/doPayment", /*previous code*/
                "http://PAYMENT-SERVICE/payment/doPayment",     /*URL of payment microservice*/
                payment,
                Payment.class);

        response = (paymentResponse.getPaymentStatus().equals("success")?
                "payment processing is successfull & order is placed": "there is a failure in payment api, order added to cart");

//        orderRepository.save(order);
//        return new TransactionResponse(order, paymentResponse.getAmount(),paymentResponse.getTransactionId(),paymentResponse.getOrderId(), response);
        return new TransactionResponse(order, paymentResponse.getAmount(),paymentResponse.getTransactionId(),paymentResponse.getOrderId(), response);
    }
}
