package org.billing.api.app.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.billing.api.app.http.helpers.HttpRequestUtils;
import org.billing.api.app.http.helpers.RequestId;
import org.billing.api.app.http.helpers.RequestIdHolder;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class TraceHttpRequestIdFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try{
            final RequestId requestId = new RequestId();
            RequestIdHolder.set(requestId);
            MDC.put(HttpRequestUtils.MDC_REQUEST_ID_KEY, RequestIdHolder.get().getBase());
            filterChain.doFilter(request, response);
        } finally{
            RequestIdHolder.remove();
            MDC.remove(HttpRequestUtils.MDC_REQUEST_ID_KEY);
        }
    }
}
