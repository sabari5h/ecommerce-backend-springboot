package com.example.Ecommerce.service;

import com.example.Ecommerce.Exception.ProductNotFound;
import com.example.Ecommerce.dto.RequestDTOs.OrderRequestDTO;
import com.example.Ecommerce.dto.RequestDTOs.OrderitemRequestDTO;
import com.example.Ecommerce.dto.ResponseDTOs.OrderItemResponseDTO;
import com.example.Ecommerce.dto.ResponseDTOs.OrderResponseDTO;
import com.example.Ecommerce.entity.*;
import com.example.Ecommerce.repository.OrderRepository;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    public String placeOrder(OrderRequestDTO orderDTO){
        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setStatus(OrderStatus.PENDING);

        for (OrderitemRequestDTO items : orderDTO.getOrderItems()){
            Product product = productRepository.findById(items.getProductId())
                    .orElseThrow(() -> new ProductNotFound("product not found"));
            if (product.getStock() < items.getQuantity()){
                return "insufficient stock for: "+ product.getName() +
                        ", Available: "+ product.getStock();
            }
        }

        double total = 0;
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderitemRequestDTO items : orderDTO.getOrderItems()){
            Product product = productRepository.findById(items.getProductId())
                    .orElseThrow(() -> new ProductNotFound("Product not found"));
            total += product.getPrice() * items.getQuantity();
            product.setStock(product.getStock() - items.getQuantity());
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(items.getQuantity());
            orderItem.setPrice(product.getPrice());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }
        User user = userRepository.findById(orderDTO.getUserId())
                        .orElseThrow(() -> new ProductNotFound("user not found"));
        order.setUser(user);
        order.setOrderItems(orderItems);
        order.setTotal(total);
        orderRepository.save(order);
        return "order placed";
    }
    public List<OrderResponseDTO> getUserOrders(long id){
         List<Order> orders = orderRepository.findByUser_Id(id);
         List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();

         for (Order order : orders){
             OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
             orderResponseDTO.setId(order.getId());
             orderResponseDTO.setStatus(order.getStatus());
             orderResponseDTO.setDate(order.getDate());
             orderResponseDTO.setTotal(order.getTotal());

             List<OrderItemResponseDTO> orderitemResponseDTO = new ArrayList<>();
             for (OrderItem orderItem : order.getOrderItems()){
                 OrderItemResponseDTO orderItemResponseDTO = new OrderItemResponseDTO();
                 orderItemResponseDTO.setProductName(orderItem.getProduct().getName());
                 orderItemResponseDTO.setQuantity(orderItem.getQuantity());
                 orderItemResponseDTO.setPrice(orderItem.getPrice());

                 orderitemResponseDTO.add(orderItemResponseDTO);
             }
             orderResponseDTO.setOrderItems(orderitemResponseDTO);
             orderResponseDTOS.add(orderResponseDTO);
         }
         return orderResponseDTOS;
    }
    @Transactional
    public String cancelOrder(long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound("no order exists"));
        if (order.getStatus() != OrderStatus.PENDING){
            return "order cannot be cancelled";
        }
        for (OrderItem items : order.getOrderItems()){
            Product product = productRepository.findById(items.getProduct().getId())
                    .orElseThrow(() -> new ProductNotFound("product not found"));
            product.setStock(product.getStock() + items.getQuantity());
        }
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        return "order cancelled";
    }
    public String updateOrderStatus(long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound("no order exists"));
        if (order.getStatus() == OrderStatus.CANCELLED){
            return "order was already cancelled";
        }else if (order.getStatus() == OrderStatus.DELIVERED){
            return "already delivered";
        }
        order.setStatus(OrderStatus.DELIVERED);
        orderRepository.save(order);
        return "delivered";
    }
}
