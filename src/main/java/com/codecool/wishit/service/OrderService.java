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

    public ProductOrder getProductOrder(Long userId) {
        return orderRepository.findByUserIdAndStatus(userId, Status.NEW);
    }

    public List<ProductOrder> getOrders(Long userId) {
        LineItem lineItem = new LineItem(2, "Cheese Soap", "cheese.jpg", 200.0f, "HUF");
        ProductOrder productOrder = new ProductOrder(userId, lineItem, Status.PAID);
        orderRepository.saveAndFlush(productOrder);
        return orderRepository.findProductOrderByUserIdAndStatus(userId, Status.PAID);
    }

    public void addToCart(Long userId, LineItem lineItem) {
        ProductOrder productOrder = getProductOrder(userId);
        if (productOrder == null) {
            productOrder = new ProductOrder(userId, lineItem, Status.NEW);
            orderRepository.saveAndFlush(productOrder);

            return;
        }
        for (LineItem lineItemInOrder : productOrder.getLineItemList()) {
            if (lineItem.getProductId() == lineItemInOrder.getProductId()) {
                return;
            }
        }
        productOrder.getLineItemList().add(lineItem);
        lineItem.setProductOrder(productOrder);
        orderRepository.saveAndFlush(productOrder);

    }

    public String closeCart(Long userId) {
        ProductOrder productOrder = getProductOrder(userId);
        if (productOrder != null){
            productOrder.setStatus(Status.CHECKEDOUT);
            return "Done";
        }
        return "No Order";

    }

    public String paidCart(Long userId) {
        ProductOrder productOrder = getProductOrder(userId);
        if (productOrder != null){
            productOrder.setStatus(Status.PAID);
            return "Done";
        }
        return "No Order";
    }
}
