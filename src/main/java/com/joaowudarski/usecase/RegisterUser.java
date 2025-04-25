package com.joaowudarski.usecase;

import com.joaowudarski.host.data.request.RegisterRequest;
import com.joaowudarski.host.data.response.LoginResponse;

public interface RegisterUser {

    LoginResponse execute(RegisterRequest registerRequest);
}
