package com.example.customerservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long id;
    private Long customerId;
    private Long productId;
    private LocalDateTime orderDate;
    private Double totalAmount;
}