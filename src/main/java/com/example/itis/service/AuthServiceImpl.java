package com.example.itis.service;

import com.example.itis.entity.UserEntity;
import com.example.itis.helper.PasswordHelper;
import com.example.itis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordHelper passwordHelper;

    @Override
    public void register(String username, String password) {
        String hashedPassword = passwordHelper.hash(password);
        UserEntity userEntity = UserEntity.builder()
                .email(username)
                .password(hashedPassword)
                .build();
        userRepository.saveUser(userEntity);
    }
}
