package com.example.repository;

import com.example.model.OrderEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface OrderRepository extends Repository<OrderEntity, OrderEntity.OrderEntityPk> {
    OrderEntity findOne(OrderEntity.OrderEntityPk orderPk);
    List<OrderEntity> findAll();
    OrderEntity saveAndFlush(OrderEntity order);
    void delete(OrderEntity.OrderEntityPk orderPk);
}
