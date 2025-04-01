package com.joaowudarski.host.data.request;

public record RegisterRequest(
        String username,
        String email,
        String password1,
        String password2
) {
}
