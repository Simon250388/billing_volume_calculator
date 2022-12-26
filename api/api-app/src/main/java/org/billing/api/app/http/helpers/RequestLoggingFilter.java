package org.billing.api.app.http.helpers;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
@Slf4j
public class RequestLoggingFilter extends CommonsRequestLoggingFilter {

    private final Set<String> doNotLogUrl;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    public RequestLoggingFilter(@Value("${logging.do-not-log-url}") Set<String> doNotLogUrl) {
        setIncludePayload(true);
        this.doNotLogUrl = doNotLogUrl;
    }

    @Override
    protected boolean isIncludeHeaders() {
        return false;
    }

    @Override
    protected boolean isIncludeQueryString() {
        return true;
    }


    @Override
    protected boolean isIncludeClientInfo() {
        return true;
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {}


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final long tineBeforeStart = System.currentTimeMillis();

        try{
            super.doFilterInternal(request, response, filterChain);
        }
        finally{
            afterRequest(request, response, tineBeforeStart);
        }
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        var URI = request.getRequestURI();
        return super.shouldLog(request) && doNotLogUrl
                .stream()
                .noneMatch(pattern -> pathMatcher.match(pattern, URI));
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) { }

    @SneakyThrows
    protected void afterRequest(HttpServletRequest request, HttpServletResponse response, long timeBeforeStart) {

        if (!shouldLog(request)) {
            return;
        }

        final long timeAfter = System.currentTimeMillis();

        final StringBuilder stringBuilder = new StringBuilder()
                .append(createMessage(request, "", ""))
                .append(", http_code=")
                .append( response.getStatus())
                .append(", payload=");

        var wrapper =  new ContentCachingResponseWrapper(response);

        stringBuilder.append(new String(wrapper.getContentInputStream().readAllBytes(), StandardCharsets.UTF_8));

        stringBuilder.append(", duration=").append((timeAfter - timeBeforeStart));

        log.debug(stringBuilder.toString());
    }
}
