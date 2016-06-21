package com.example.service;

import com.example.model.UserEntity;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Long join(UserEntity userEntity) {
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
