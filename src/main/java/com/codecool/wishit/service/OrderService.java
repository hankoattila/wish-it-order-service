package com.codecool.wishit.service;


import com.codecool.wishit.model.LineItem;
import com.codecool.wishit.model.ProductOrder;
import com.codecool.wishit.model.Status;
import com.codecool.wishit.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    OrderRepository orderRepository;

    public ProductOrder getOrders(Long userId) {
        List<LineItem> lineItems = new ArrayList<>();
        LineItem lineItem = new LineItem(1,  "Light Saber", "lightsaber.jpg", 5000.0f, "USD");
        LineItem lineItem1 = new LineItem(1,  "Fight Club Soap", "soap.jpg", 100.0f, "USD");
        lineItems.add(lineItem);
        lineItems.add(lineItem1);
        ProductOrder order = new ProductOrder(lineItems, 555.0f);
        order.setStatus(Status.NEW);
        lineItem.setProductOrder(order);
        lineItem1.setProductOrder(order);
        orderRepository.saveAndFlush(order);
        return order;
    }
}
