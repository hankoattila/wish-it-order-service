package com.codecool.wishit.controller;

import com.codecool.wishit.model.*;
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

    @PostMapping("/")
    public void getJsonFromGreg(@RequestBody String json) {
        System.out.println(json);
    }

    @GetMapping("/order/{userId}")
    public ProductOrder getOpenOrder(@PathVariable("userId") Long userId) {
        return orderService.getProductOrder(userId, Status.NEW);
    }


    @GetMapping("/orders/{userId}")
    public List<ProductOrder> getCloseOrders(@PathVariable("userId") Long userId) {

        return orderService.getOrders(userId,Status.PAID);
    }

    @PostMapping("/api/add-to-cart")
    public String addToCart(@RequestBody String json) {
        Product product = JSONParser.parsToObject(json, "product", Product.class);
        String user_id = JSONParser.getDataByKey(json, "user_id");
        LineItem lineItem = new LineItem(product.getId(),
                product.getName(),
                product.getImageFileName(),
                product.getDefaultPrice(),
                product.getDefaultCurrency());
        try{

            orderService.addToCart(Long.parseLong(user_id), lineItem);
        } catch (Exception e){
            return "invalid_params";
        }
        return "new_item";

    }

    @PostMapping("/api/checkout-the-cart")
    public void closeCart(@RequestBody String json) {
        String user_id = JSONParser.getDataByKey(json, "user_id");
        String message = orderService.closeCart(Long.parseLong(user_id));

    }

    @PostMapping("/api/paid-the-cart")
    public void paidCatt(@RequestBody String json) {
        String user_id = JSONParser.getDataByKey(json, "user_id");
        String message = orderService.paidCart(Long.parseLong(user_id));

    }


    @PostMapping("/api/remove-from-cart")
    public void removeFromCart(@RequestBody String json) {
        Product product = JSONParser.parsToObject(json, "product", Product.class);
        String user_id = JSONParser.getDataByKey(json, "user_id");
        String message =  orderService.removeFromCart(Long.parseLong(user_id), (long) product.getId());

    }

}
