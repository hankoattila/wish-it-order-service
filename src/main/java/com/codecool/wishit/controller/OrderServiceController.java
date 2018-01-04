package com.codecool.wishit.controller;

import com.codecool.wishit.model.ProductOrder;
import com.codecool.wishit.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderServiceController {
    OrderService orderService;


    public OrderServiceController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/order/{userId}")
    public ProductOrder post(@PathVariable("userId") String userId) {
        System.out.println(userId);
        return orderService.getOrders(3L);

    }

}
