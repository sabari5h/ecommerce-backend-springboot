package com.example.Ecommerce.dto.ResponseDTOs;

import com.example.Ecommerce.entity.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public class OrderResponseDTO {
    private long id;
    private OrderStatus status;
    private double total;
    private LocalDate date;
    private String username;
    private List<OrderItemResponseDTO> orderItems;

    public OrderResponseDTO() {

    }

    public OrderResponseDTO(long id, OrderStatus status, double total, LocalDate date, String username, List<OrderItemResponseDTO> orderItems) {
        this.id = id;
        this.status = status;
        this.total = total;
        this.date = date;
        this.username = username;
        this.orderItems = orderItems;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public List<OrderItemResponseDTO> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItemResponseDTO> orderItems) { this.orderItems = orderItems; }
}
