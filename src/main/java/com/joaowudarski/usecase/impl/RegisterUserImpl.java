package com.joaowudarski.usecase.impl;

import com.br.jvcw.usecase.TokenService;
import com.joaowudarski.exception.InvalidAuthenticationException;
import com.joaowudarski.gateway.repository.UserRepository;
import com.joaowudarski.gateway.repository.data.UserEntity;
import com.joaowudarski.host.data.request.RegisterRequest;
import com.joaowudarski.host.data.response.LoginResponse;
import com.joaowudarski.usecase.RegisterUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegisterUserImpl implements RegisterUser {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    public LoginResponse execute(RegisterRequest registerRequest) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsernameOrEmail(registerRequest.username(), registerRequest.email());

        if (userEntityOptional.isPresent())
            throw new InvalidAuthenticationException("User already exists");
        if (!registerRequest.password1().equals(registerRequest.password2()))
            throw new InvalidAuthenticationException("Passwords doesnt coincides");

        UserEntity userEntity = UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .email(registerRequest.email())
                .rolePermission("USER")
                .password(Base64.getEncoder().encodeToString(registerRequest.password1().getBytes(StandardCharsets.UTF_8)))
                .username(registerRequest.username())
                .build();

        userEntity = userRepository.save(userEntity);
        String token = tokenService.generateToken(userEntity);
        return new LoginResponse(token, userEntity.getUsername());
    }
}
