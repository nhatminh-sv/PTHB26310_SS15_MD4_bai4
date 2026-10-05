package com.example.customerservice.controller;

import com.example.customerservice.dto.OrderDTO;
import com.example.customerservice.dto.OrderRequest;
import com.example.customerservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createOrder(
            @Valid @RequestBody OrderRequest request
    ) {
        return orderService.createOrder(request);
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(
            @PathVariable Long id
    ) {
        return orderService.getOrderById(id);
    }
}