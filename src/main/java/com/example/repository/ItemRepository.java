package com.example.repository;

import com.example.model.ItemEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ItemRepository extends Repository<ItemEntity, Long> {
    ItemEntity findOne(Long id);
    List<ItemEntity> findByName(String name);
    ItemEntity saveAndFlush(ItemEntity user);
    void delete(Long id);
}
