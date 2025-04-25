package com.joaowudarski.host.data.response;


public record LoginResponse(
        String token,
        String username
) {

    public static LoginResponse byDomain(String username, String token) {
        return new LoginResponse(username, token);
    }
}
