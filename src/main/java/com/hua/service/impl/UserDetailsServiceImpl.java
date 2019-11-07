package com.hua.service.impl;

import com.hua.domain.User;
import com.hua.domain.JwtUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by echisan on 2018/6/23
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService,com.hua.service.UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean regist(String name, String password, String privilege) {
        return jdbcTemplate.update("insert into user(username,password,role) values(?,?,?)", name, password,
                privilege) > 0;
    }

    @Override
    public Boolean changePWD(String username, String encodedNewPassWord) {
        return jdbcTemplate.update("UPDATE user SET password=? WHERE username=?", encodedNewPassWord, username) > 0;
    }

    @Override
    public User getUserById(Integer id) {
        List<User> res = jdbcTemplate.query("select * from user where id=?", new Object[] { id },
                new BeanPropertyRowMapper<>(User.class));
        if (res.isEmpty()) {
            return null;
        }
        return res.get(0);
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<User> res = jdbcTemplate.query("select * from user where username=?", new Object[] { s },
                new BeanPropertyRowMapper<>(User.class));
        return new JwtUser(res.get(0));
    }

}
