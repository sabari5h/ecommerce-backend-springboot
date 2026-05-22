package com.example.Ecommerce.dto.RequestDTOs;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class OrderRequestDTO {

    @Positive(message = "id is required")
    private long userId;
    @NotNull
    @Valid
    private List<OrderitemRequestDTO> orderItems;

    public OrderRequestDTO(){

    }

    public OrderRequestDTO(long user, List<OrderitemRequestDTO> orderItems) {
        this.userId = user;
        this.orderItems = orderItems;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long user) {
        this.userId = user;
    }

    public List<OrderitemRequestDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderitemRequestDTO> orderItems) {
        this.orderItems = orderItems;
    }
}
