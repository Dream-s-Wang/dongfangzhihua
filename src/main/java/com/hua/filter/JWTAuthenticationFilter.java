package com.hua.filter;

import com.hua.domain.JwtUser;
import com.hua.model.LoginUser;
import com.hua.utils.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ThreadLocal<Integer> rememberMe = new ThreadLocal<>();
    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response)
            throws AuthenticationException {

        final LoginUser loginUser = new LoginUser();
        loginUser.setPassword(request.getParameter("password"));
        loginUser.setUsername(request.getParameter("username"));
        loginUser.setRememberMe(Integer.parseInt(request.getParameter("rememberMe")));
        System.out.println(loginUser.toString());
        rememberMe.set(loginUser.getRememberMe());
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(),
                loginUser.getPassword(), new ArrayList<>()));

    }


    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
                                            final FilterChain chain, final Authentication authResult) throws IOException, ServletException {
        final JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        System.out.println("jwtUser:" + jwtUser.toString());
        final boolean isRemember = rememberMe.get() == 1;
        String role = "";
        final Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for (final GrantedAuthority authority : authorities) {
            role = authority.getAuthority();
        }
        final String token = JwtTokenUtils.createToken(jwtUser.getUsername(), role, isRemember);
        response.getWriter().print("{taken:" + JwtTokenUtils.TOKEN_PREFIX + token + "}");
    }

    @Override
    protected void unsuccessfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
                                              final AuthenticationException failed) throws IOException, ServletException {
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }
}
