package com.joaowudarski.usecase;

import com.joaowudarski.media.MediaType;

public interface DeleteMediaRegister {
    void execute(MediaType mediaType, String id);
}