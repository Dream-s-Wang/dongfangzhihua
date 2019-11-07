package com.hua.domain;


import lombok.Data;

@Data
public class User{

    Integer id;
    String username;
    String password;
    private String role;
}