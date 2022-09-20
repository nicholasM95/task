package com.example.task.utils;

import com.sun.security.auth.UserPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.*;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithCustomUser> {

    @Override
    public SecurityContext createSecurityContext(WithCustomUser customUser) {
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("id", customUser.id());
        userDetails.put("name", customUser.name());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        UserPrincipal principal = new UserPrincipal(customUser.name());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, (Object) null, this.getUserRoles(customUser.role()));
        authentication.setDetails(userDetails);
        context.setAuthentication(authentication);
        return context;
    }

    private Collection<GrantedAuthority> getUserRoles(String role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        return grantedAuthorities;
    }
}

