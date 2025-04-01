package com.joaowudarski.usecase.impl;

import com.br.jvcw.usecase.TokenService;
import com.br.jvcw.usecase.impl.TokenServiceImpl;
import com.joaowudarski.exception.InvalidAuthenticationException;
import com.joaowudarski.gateway.repository.UserRepository;
import com.joaowudarski.gateway.repository.data.UserEntity;
import com.joaowudarski.host.data.request.LoginRequest;
import com.joaowudarski.host.data.response.LoginResponse;
import com.joaowudarski.usecase.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class LoginUserImpl implements LoginUser {

    private final UserRepository userRepository;
    private final TokenServiceImpl tokenService;

    @Override
    public LoginResponse execute(LoginRequest loginRequest) {
        UserEntity userEntity = userRepository.findByUsername(loginRequest.username())
                .orElseThrow(() -> new InvalidAuthenticationException("User or password invalid"));

        String encodedPassword = Base64.getEncoder().encodeToString(loginRequest.password().getBytes(StandardCharsets.UTF_8));
        if (userEntity.getPassword().equals(encodedPassword)) {
            String token = tokenService.generateToken(userEntity);
            return new LoginResponse(token, userEntity.getUsername());
        }

        throw new InvalidAuthenticationException("User or password invalid");
    }
}
