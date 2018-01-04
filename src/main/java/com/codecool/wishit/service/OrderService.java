package com.codecool.wishit.service;


import com.codecool.wishit.model.LineItem;
import com.codecool.wishit.model.Product;
import com.codecool.wishit.model.ProductOrder;
import com.codecool.wishit.model.Status;
import com.codecool.wishit.repository.OrderRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ProductOrder getProductOrder(Long userId,Status status) {
        return orderRepository.findByUserIdAndStatus(userId, status);
    }

    public List<ProductOrder> getOrders(Long userId,Status status) {
        return orderRepository.findProductOrderByUserIdAndStatus(userId, status);
    }

    public ProductOrder getProductOrderByUserId(Long userId){
        return orderRepository.findByUserId(userId);
    }

    public void addToCart(Long userId, LineItem lineItem) {
        ProductOrder productOrder = getProductOrder(userId,Status.NEW);
        if (productOrder == null) {
            productOrder = new ProductOrder(userId, lineItem, Status.NEW);
            orderRepository.saveAndFlush(productOrder);

            return;
        }
        for (LineItem lineItemInOrder : productOrder.getItems()) {
            if (lineItem.getProductId() == lineItemInOrder.getProductId()) {
                return;
            }
        }
        productOrder.getItems().add(lineItem);
        lineItem.setProductOrder(productOrder);
        orderRepository.saveAndFlush(productOrder);

    }

    public String closeCart(Long userId) {
        ProductOrder productOrder = getProductOrder(userId,Status.NEW);
        if (productOrder != null){
            productOrder.setStatus(Status.CHECKEDOUT);
            orderRepository.saveAndFlush(productOrder);
            return "Done";
        }
        return "No Order";

    }

    public String paidCart(Long userId) {
        ProductOrder productOrder = getProductOrder(userId,Status.CHECKEDOUT);
        if (productOrder != null){
            productOrder.setStatus(Status.PAID);
            return "Done";
        }
        return "No Order";
    }

    public String removeFromCart(Long userId,Long productId) {
        ProductOrder productOrder = getProductOrder(userId,Status.NEW);
        if (productOrder != null){
            for (LineItem lineItemInOrder : productOrder.getItems()) {
                if (productId == lineItemInOrder.getProductId()) {
                    productOrder.getItems().remove(lineItemInOrder);
                    orderRepository.saveAndFlush(productOrder);
                    return "Remove Done";
                }
            }
        }
        return "No Order";
    }
}
