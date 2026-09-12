package com.example.itis.service;

import com.example.itis.entity.UserEntity;
import com.example.itis.exception.UserAlreadyExistsException;
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
    public void register(String email, String password) {
        String hashedPassword = passwordHelper.hash(password);
        UserEntity userEntity = UserEntity.builder()
                .email(email)
                .password(hashedPassword)
                .build();
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException(email);
        }
        userRepository.save(userEntity);
    }
}
