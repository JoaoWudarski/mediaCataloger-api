package com.joaowudarski.domain;

import lombok.Data;

@Data
public class User {

    private String id;
    private String username;
    private String email;
    private String password;
    private String rolePermission;

}
