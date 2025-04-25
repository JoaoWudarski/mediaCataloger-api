package com.joaowudarski.usecase.impl;

import com.joaowudarski.media.MediaType;
import com.joaowudarski.usecase.DeleteMediaRegister;
import com.joaowudarski.usecase.SearchMedia;
import com.joaowudarski.exception.ObjectUpdateException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteMediaRegisterImpl implements DeleteMediaRegister {

    private final ApplicationContext applicationContext;
    private final SearchMedia searchMedia;

    @Override
    public void execute(MediaType mediaType, String id) {
        if (searchMedia.byId(mediaType, id).isEmpty())
            throw new ObjectUpdateException("Media not found with id: " + id);

        Class<?> repositoryClass = mediaType.getRepository();
        JpaRepository<?, String> repository = (JpaRepository<?, String>) applicationContext.getBean(repositoryClass);
        repository.deleteById(id);
    }
}