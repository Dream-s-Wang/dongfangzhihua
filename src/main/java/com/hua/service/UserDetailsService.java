package com.hua.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.hua.domain.User;

public interface UserDetailsService {
    /**
     * 通过用户名获取用户
     * @param name
     * @return
     */
    User getUserById(Integer id);

    /**
     * 用户注册
     * @param name
     * @param password
     * @param privilege
     * @return
     */
    Boolean regist(String name,String password,String privilege);

    /**
     * 修改密码
     * @param username
     * @param encodedNewPassWord 加密后的新密码
     * @return
     */
    Boolean changePWD(String username,String encodedNewPassWord);

    public UserDetails loadUserByUsername(String s);
}