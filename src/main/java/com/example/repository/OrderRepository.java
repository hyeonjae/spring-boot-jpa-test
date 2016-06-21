package com.example.repository;

import com.example.model.OrderEntity;
import org.springframework.data.repository.Repository;

public interface OrderRepository extends Repository<OrderEntity, OrderEntity.OrderEntityPk> {
    OrderEntity findOne(OrderEntity.OrderEntityPk orderPk);
    OrderEntity saveAndFlush(OrderEntity order);
    void delete(OrderEntity.OrderEntityPk orderPk);
}
