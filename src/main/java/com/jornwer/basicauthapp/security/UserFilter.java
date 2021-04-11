package com.jornwer.basicauthapp.security;

import com.jornwer.basicauthapp.repository.DeletedUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class UserFilter extends GenericFilterBean {
    private final DeletedUserRepository deletedUserRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && deletedUserRepository.findByEmail(auth.getName()).isPresent()) {
            auth.setAuthenticated(false);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
