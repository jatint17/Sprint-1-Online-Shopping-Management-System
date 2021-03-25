package com.cg.onlineshoppingms.productms.dto;

public class UpdatePriceRequest
{
    private Long productId;
    private double newPrice;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }
}
