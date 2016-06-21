package com.example.service;

import com.example.model.ItemEntity;
import com.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public Long add(ItemEntity itemEntity) {
        itemEntity = itemRepository.saveAndFlush(itemEntity);
        return itemEntity.getId();
    }

    public List<ItemEntity> findByName(String name) {
        List<ItemEntity> itemEntities = itemRepository.findByName(name);
        return itemEntities;
    }

    public ItemEntity findOne(Long id) {
        ItemEntity itemEntity = itemRepository.findOne(id);
        return itemEntity;
    }
}
