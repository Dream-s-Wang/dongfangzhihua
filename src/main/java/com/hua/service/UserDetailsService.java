//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hua.service;

import com.hua.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {
    User getUserById(Integer id);

    Boolean regist(String name, String password, String privilege);

    Boolean changePWD(String username, String encodedNewPassWord);

    UserDetails loadUserByUsername(String s);
}
