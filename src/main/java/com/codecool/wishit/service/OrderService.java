package com.codecool.wishit.service;


import com.codecool.wishit.model.LineItem;
import com.codecool.wishit.model.ProductOrder;
import com.codecool.wishit.model.Status;
import com.codecool.wishit.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ProductOrder getOrders(Long userId) {
        return orderRepository.findByUserIdAndStatus(userId, Status.NEW);
    }

    public void addToCart(Long userId, LineItem lineItem) throws IOException {
        ProductOrder productOrder = getOrders(userId);
        if (productOrder == null){
            productOrder = new ProductOrder(userId);
            return;
        }
        for (LineItem lineItemInOrder:productOrder.getLineItemList()){
            if (lineItem.getProductId() == lineItemInOrder.getProductId()){
                return;
            }
        }
        productOrder.getLineItemList().add(lineItem);
        lineItem.setProductOrder(productOrder);
        orderRepository.saveAndFlush(productOrder);

    }

    public void initOrder(Long userId) {
        List<LineItem> lineItems = new ArrayList<>();
        LineItem lineItem = new LineItem(1, "Light Saber", "lightsaber.jpg", 5000.0f, "USD");
        lineItems.add(lineItem);
        ProductOrder order = new ProductOrder(lineItems, 555.0f);
        order.setStatus(Status.NEW);
        lineItem.setProductOrder(order);
        orderRepository.saveAndFlush(order);
    }
}
