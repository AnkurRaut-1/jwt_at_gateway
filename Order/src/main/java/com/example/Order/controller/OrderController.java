package com.example.Order.controller;

import com.example.Order.common.TransactionRequest;
import com.example.Order.common.TransactionResponse;
import com.example.Order.entity.Order;
import com.example.Order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest){
        return orderService.saveOrder(transactionRequest);
        ////call to payment microservice and pass order id
    }
}
