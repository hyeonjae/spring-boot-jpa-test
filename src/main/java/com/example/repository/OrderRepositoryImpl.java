package com.example.repository;

import com.example.model.OrderEntity;
import com.example.model.QOrderEntity;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

public class OrderRepositoryImpl extends QueryDslRepositorySupport implements OrderRepositoryCustom {
    public OrderRepositoryImpl() {
        super(OrderEntity.class);
    }

    @Override
    @Transactional
    public void deleteBy(OrderEntity.OrderEntityPk orderPk) {
        QOrderEntity order = QOrderEntity.orderEntity;

        delete(order)
                .where(order.orderEntityPk.userId.eq(orderPk.getUserId()))
                .where(order.orderEntityPk.itemId.eq(orderPk.getItemId()))
                .execute();
    }

    @Override
    @Transactional
    public void deleteBy(Long userId, Long itemId) {
        QOrderEntity order = QOrderEntity.orderEntity;

        delete(order)
                .where(order.orderEntityPk.userId.eq(userId))
                .where(order.orderEntityPk.itemId.eq(itemId))
                .execute();
    }

}
