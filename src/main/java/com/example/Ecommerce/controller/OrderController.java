package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.RequestDTOs.OrderRequestDTO;
import com.example.Ecommerce.dto.ResponseDTOs.OrderResponseDTO;
import com.example.Ecommerce.entity.Order;
import com.example.Ecommerce.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("")
    public String placeOrder(@Valid @RequestBody OrderRequestDTO order){
        return orderService.placeOrder(order);
    }
    @GetMapping("/{id}")
    public List<OrderResponseDTO> getUserOrders(@PathVariable long id){
        return orderService.getUserOrders(id);
    }
    @PutMapping("/{id}/cancel")
    public String cancelOrder(@PathVariable long id){
        return orderService.cancelOrder(id);
    }
    @PutMapping("/{id}")
    public String updateOrderStatus(@PathVariable long id){
        return orderService.updateOrderStatus(id);
    }
}
