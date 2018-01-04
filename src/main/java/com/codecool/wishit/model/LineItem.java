package com.codecool.wishit.model;

import javax.persistence.*;
import java.util.Currency;

@Entity
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int productId;
    private float actualPrice;
    private String productName;
    private String productImage;
    private Currency currency;

    @ManyToOne
    private ProductOrder productOrder;

    public LineItem() {
    }

    public LineItem(int productId, String productName, String productImage, float actualPrice, Currency currency) {
        this.productId = productId;
        this.actualPrice = actualPrice;
        this.productName = productName;
        this.productImage = productImage;
        this.currency = currency;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductOrder() {
        return productOrder.getId().toString();
    }

    public void setProductOrder(ProductOrder productOrder) {
        this.productOrder = productOrder;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(float actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
