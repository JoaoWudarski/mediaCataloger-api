package com.joaowudarski.usecase;

import com.joaowudarski.host.data.request.LoginRequest;
import com.joaowudarski.host.data.response.LoginResponse;

public interface LoginUser {

    LoginResponse execute(LoginRequest loginRequest);
}
