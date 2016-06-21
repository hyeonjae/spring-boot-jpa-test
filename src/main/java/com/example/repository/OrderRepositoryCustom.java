package com.example.repository;

import com.example.model.OrderEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface OrderRepositoryCustom {
    void deleteBy(OrderEntity.OrderEntityPk orderPk);
    void deleteBy(Long userId, Long itemId);

}
