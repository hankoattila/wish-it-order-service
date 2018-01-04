package com.codecool.wishit.controller;

import com.codecool.wishit.model.LineItem;
import com.codecool.wishit.model.Product;
import com.codecool.wishit.model.ProductOrder;
import com.codecool.wishit.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
public class OrderServiceController {
    OrderService orderService;


    public OrderServiceController(OrderService orderService) {
        this.orderService = orderService;
    }



    @GetMapping("/order/{userId}")
    public ProductOrder getOpenOrder(@PathVariable("userId") Long userId){
        return orderService.getProductOrder(userId);
    }


    @GetMapping("/orders/{userId}")
    public List<ProductOrder> getCloseOrders(@PathVariable("userId") Long userId) {
        LineItem lineItem = new LineItem(2, "Cheese Soap", "cheese.jpg", 200.0f, "HUF");
        ProductOrder productOrder = new ProductOrder(userId, lineItem);


        return orderService.getOrders(userId);
    }

    @PostMapping("/api/add-to-cart")
    public void addToCart(){
        LineItem lineItem = new LineItem(1, "Fight Club Soap", "soap.jpg", 100.0f, "USD");
        orderService.addToCart(1L,lineItem);
    }

    @PostMapping("/api/remove-from-cart")
    public void removeFromCart(){

    }

}
