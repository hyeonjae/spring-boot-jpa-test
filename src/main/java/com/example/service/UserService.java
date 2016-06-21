package com.example.service;

import com.example.model.UserEntity;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public Long join(Long id, String name) {
        UserEntity userEntity = new UserEntity(id, name);
        userEntity = userRepository.saveAndFlush(userEntity);
        return userEntity.getId();
    }

    public List<UserEntity> findByName(String name) {
        List<UserEntity> userEntities = userRepository.findByName(name);
        return userEntities;
    }

    public UserEntity findOne(Long id) {
        UserEntity userEntity = userRepository.findOne(id);
        return userEntity;
    }
}
