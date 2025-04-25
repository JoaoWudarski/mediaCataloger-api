package com.joaowudarski.usecase;

import com.joaowudarski.media.AbstractMedia;

public interface UpdateMediaRegister {

    String execute(AbstractMedia actualMedia, AbstractMedia newMedia);
}
