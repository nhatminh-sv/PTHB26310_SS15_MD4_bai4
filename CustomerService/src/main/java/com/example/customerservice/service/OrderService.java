package com.example.customerservice.service;

import com.example.customerservice.dto.OrderDTO;
import com.example.customerservice.dto.OrderRequest;
import com.example.customerservice.entity.Order;
import com.example.customerservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderDTO createOrder(OrderRequest request) {

        // Giả lập giá sản phẩm lấy từ Product Service
        double productPrice = 500000;

        double totalAmount = productPrice * request.getQuantity();

        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setProductId(request.getProductId());
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        return new OrderDTO(
                savedOrder.getId(),
                savedOrder.getCustomerId(),
                savedOrder.getProductId(),
                savedOrder.getOrderDate(),
                savedOrder.getTotalAmount()
        );
    }

    public OrderDTO getOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return new OrderDTO(
                order.getId(),
                order.getCustomerId(),
                order.getProductId(),
                order.getOrderDate(),
                order.getTotalAmount()
        );
    }
}