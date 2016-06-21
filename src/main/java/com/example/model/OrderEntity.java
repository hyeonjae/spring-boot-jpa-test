package com.example.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OrderEntity implements Serializable {

    @EmbeddedId
    OrderEntityPk orderEntityPk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    ItemEntity itemEntity;

    public OrderEntity() {
    }

    public OrderEntity(Long userId, Long itemId) {
        this.orderEntityPk = new OrderEntityPk(userId, itemId);
    }

    public OrderEntity(UserEntity userEntity, ItemEntity itemEntity) {
        this.userEntity = userEntity;
        this.itemEntity = itemEntity;
        this.orderEntityPk = new OrderEntityPk(userEntity.getId(), itemEntity.getId());
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

    public OrderEntityPk getOrderEntityPk() {
        return orderEntityPk;
    }

    public void setOrderEntityPk(OrderEntityPk orderEntityPk) {
        this.orderEntityPk = orderEntityPk;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderEntityPk=" + orderEntityPk +
                '}';
    }

    @Embeddable
    public static class OrderEntityPk implements Serializable {
        @Column(name = "user_id")
        Long userId;

        @Column(name="item_id")
        Long itemId;

        public OrderEntityPk() {
        }

        public OrderEntityPk(Long userId, Long itemId) {
            this.userId = userId;
            this.itemId = itemId;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getItemId() {
            return itemId;
        }

        public void setItemId(Long itemId) {
            this.itemId = itemId;
        }

        @Override
        public String toString() {
            return "OrderEntityPk{" +
                    "userId=" + userId +
                    ", itemId=" + itemId +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            OrderEntityPk orderEntityPk = (OrderEntityPk) o;

            if (userId != null ? !userId.equals(orderEntityPk.userId) : orderEntityPk.userId != null) return false;
            return !(itemId != null ? !itemId.equals(orderEntityPk.itemId) : orderEntityPk.itemId != null);

        }

        @Override
        public int hashCode() {
            int result = userId != null ? userId.hashCode() : 0;
            result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
            return result;
        }
    }
}
