package com.codecool.wishit.controller;

import com.codecool.wishit.model.LineItem;
import com.codecool.wishit.model.ProductOrder;
import com.codecool.wishit.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
public class OrderServiceController {
    OrderService orderService;


    public OrderServiceController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/init")
    public void init(){
        orderService.initOrder(1L);
    }

    @GetMapping("/order/{userId}")
    public ProductOrder get(@PathVariable("userId") Long userId) {
        return orderService.getOrders(userId);
    }

    @PostMapping("/order")
    public void post() throws IOException {
        LineItem lineItem = new LineItem(1, "Fight Club Soap", "soap.jpg", 100.0f, "USD");
        orderService.addToCart(1L,lineItem);
    }

}
