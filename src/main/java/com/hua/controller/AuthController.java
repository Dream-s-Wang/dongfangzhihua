//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hua.controller;

import com.hua.domain.User;
import com.hua.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/auth"})
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthController() {
    }

    @PostMapping({"/register"})
    public UserDetails registerUser(User user) {
        System.out.println(user.toString());
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        System.out.println(user.toString());
        this.userDetailsService.regist(user.getUsername(), user.getPassword(), user.getRole());
        return this.userDetailsService.loadUserByUsername(user.getUsername());
    }
}
