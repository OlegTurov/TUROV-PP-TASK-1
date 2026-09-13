package com.example.itis.service;

import com.example.itis.entity.UserEntity;
import com.example.itis.exception.UserAlreadyExistsException;
import com.example.itis.exception.UserNotExistsException;
import com.example.itis.exception.UserPasswordIncorrectException;
import com.example.itis.helper.PasswordHelper;
import com.example.itis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordHelper passwordHelper;
    private final SessionService sessionService;
    private final static Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Override
    public void register(String email, String password) {
        String hashedPassword = passwordHelper.hash(password);
        UserEntity userEntity = UserEntity.builder()
                .email(email)
                .password(hashedPassword)
                .build();
        log.info("Checking of existing user with email {}", email);
        if (userRepository.existsByEmail(email)) {
            log.error("User with email {} already exists", email);
            throw new UserAlreadyExistsException(email);
        }
        userRepository.save(userEntity);
        log.info("Registered user with email {}", email);
    }

    @Override
    public String login(String email, String password) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotExistsException(email));

        if (!passwordHelper.matches(password, user.getPassword())) {
            throw new UserPasswordIncorrectException(email);
        }

        return sessionService.createSession(user.getId());
    }
}
