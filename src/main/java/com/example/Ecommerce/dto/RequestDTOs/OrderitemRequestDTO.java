package com.example.Ecommerce.dto.RequestDTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public class OrderitemRequestDTO {

    @Positive(message = "product id required")
    private long productId;

    @Min(value = 1, message = "minimum one quantity is required")
    private int quantity;

    public OrderitemRequestDTO(){

    }

    public OrderitemRequestDTO(long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
