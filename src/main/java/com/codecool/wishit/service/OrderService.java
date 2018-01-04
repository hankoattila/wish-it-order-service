package com.codecool.wishit.service;


import com.codecool.wishit.model.LineItem;
import com.codecool.wishit.model.ProductOrder;
import com.codecool.wishit.model.Status;
import com.codecool.wishit.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ProductOrder getOrders(Long userId) {
        return orderRepository.findByUserIdAndStatus(userId, Status.NEW);
    }

    public void addToCart(Long userId, LineItem lineItem) {
        ProductOrder productOrder = getOrders(userId);
        if (productOrder == null) {
            productOrder = new ProductOrder(userId, lineItem);
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

}
