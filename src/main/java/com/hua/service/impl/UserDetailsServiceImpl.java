//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hua.service.impl;

import com.hua.domain.JwtUser;
import com.hua.domain.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, com.hua.service.UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDetailsServiceImpl() {
    }

    @Override
    public Boolean regist(String name, String password, String privilege) {
        return this.jdbcTemplate.update("insert into user(username,password,role) values(?,?,?)",
                new Object[] { name, password, privilege }) > 0;
    }

    @Override
    public Boolean changePWD(String username, String encodedNewPassWord) {
        return this.jdbcTemplate.update("UPDATE user SET password=? WHERE username=?",
                new Object[] { encodedNewPassWord, username }) > 0;
    }

    @Override
    public User getUserById(Integer id) {
        List<User> res = this.jdbcTemplate.query("select * from user where id=?", new Object[] { id },
                new BeanPropertyRowMapper(User.class));
        return res.isEmpty() ? null : (User) res.get(0);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<User> res = this.jdbcTemplate.query("select * from user where username=?", new Object[] { s },
                new BeanPropertyRowMapper(User.class));
        return new JwtUser((User) res.get(0));
    }
}
