package com.joaowudarski.usecase.impl;

import com.br.jvcw.usecase.TokenService;
import com.joaowudarski.media.AbstractMedia;
import com.joaowudarski.gateway.repository.UserRepository;
import com.joaowudarski.gateway.repository.data.UserEntity;
import com.joaowudarski.usecase.CreateMediaRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateMediaRegisterImpl implements CreateMediaRegister {

    private final ApplicationContext applicationContext;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    public String execute(AbstractMedia abstractMedia) {
        UserEntity user = userRepository.findById(tokenService.getUserId()).orElse(null);
        abstractMedia.setId(UUID.randomUUID().toString());
        abstractMedia.setUser(user);

        Class<?> repositoryClass = abstractMedia.getMediaType().getRepository();
        JpaRepository<AbstractMedia, String> repository =
                (JpaRepository<AbstractMedia, String>) applicationContext.getBean(repositoryClass);

        return repository.save(abstractMedia).getId();
    }
}
