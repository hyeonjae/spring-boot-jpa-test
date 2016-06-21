package com.example.service;

import com.example.model.OrderEntity;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public OrderEntity order(OrderEntity order) {
        return orderRepository.saveAndFlush(order);
    }
}
