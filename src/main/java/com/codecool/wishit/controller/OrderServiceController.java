package com.codecool.wishit.controller;

import com.codecool.wishit.model.LineItem;
import com.codecool.wishit.model.Product;
import com.codecool.wishit.model.ProductOrder;
import com.codecool.wishit.model.User;
import com.codecool.wishit.service.OrderService;
import com.codecool.wishit.utilities.JSONParser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Line;
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

        return orderService.getOrders(userId);
    }

    @PostMapping("/api/add-to-cart")
    public void addToCart(@RequestBody String json){
        Product product = JSONParser.parsToObject(json,"product",Product.class);
        System.out.println(product.getType());
        System.out.println(product.isReserved());
        LineItem lineItem = new LineItem(1, "Fight Club Soap", "soap.jpg", 100.0f, "USD");

        orderService.addToCart(1L,lineItem);
    }

    @PostMapping("/api/checkout-the-cart")
    public void closeCart(){
        String message =  orderService.closeCart(1L);
        System.out.println(message);
    }

    @PostMapping("/api/paid-the-cart")
    public void paidCatt(){
        String message =  orderService.paidCart(1L);
        System.out.println(message);
    }


    @PostMapping("/api/remove-from-cart")
    public void removeFromCart(){

    }

}
