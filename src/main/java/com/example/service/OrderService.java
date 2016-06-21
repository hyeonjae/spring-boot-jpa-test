package com.example.service;

import com.example.model.OrderEntity;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public OrderEntity order(OrderEntity order) {
        return orderRepository.saveAndFlush(order);
    }

    public OrderEntity findOne(OrderEntity.OrderEntityPk orderPk) {
        return orderRepository.findOne(orderPk);
    }

    public void delete(OrderEntity order) {
        orderRepository.delete(order.getOrderEntityPk());
    }

    public List<OrderEntity> showAll() {
        return orderRepository.findAll();
    }
}
