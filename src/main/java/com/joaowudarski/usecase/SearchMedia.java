package com.joaowudarski.usecase;

import com.joaowudarski.media.AbstractMedia;
import com.joaowudarski.media.MediaType;

import java.util.Optional;

public interface SearchMedia {

    Optional<AbstractMedia> byId(MediaType mediaType, String id);
}
