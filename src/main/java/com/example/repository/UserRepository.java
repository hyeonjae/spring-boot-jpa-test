package com.example.repository;

import com.example.model.UserEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends Repository<UserEntity, Long> {
    UserEntity findOne(Long id);
    List<UserEntity> findByName(String name);
    UserEntity saveAndFlush(UserEntity userEntity);
    void delete(Long id);
}
