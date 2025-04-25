package com.joaowudarski.usecase.impl;

import com.joaowudarski.media.AbstractMedia;
import com.joaowudarski.media.MediaType;
import com.joaowudarski.usecase.SearchMedia;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SearchMediaImpl implements SearchMedia {

    private final ApplicationContext applicationContext;

    @Override
    public Optional<AbstractMedia> byId(MediaType mediaType, String id) {
        Class<?> repositoryClass = mediaType.getRepository();
        JpaRepository<AbstractMedia, String> repository =
                (JpaRepository<AbstractMedia, String>) applicationContext.getBean(repositoryClass);

        return repository.findById(id);
    }
}
