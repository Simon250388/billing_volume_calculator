package org.billing.api.app.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.MDC;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.web.filter.OncePerRequestFilter;

public class MyCustomAuthFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
            .getContextHolderStrategy();

    public MyCustomAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Authentication authenticationResult = authenticationManager.authenticate(new CustomAuthToken());

        MDC.put("uid", authenticationResult.getName());

        SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();

        context.setAuthentication(authenticationResult);

        this.securityContextHolderStrategy.setContext(context);

        filterChain.doFilter(request, response);
    }
}
