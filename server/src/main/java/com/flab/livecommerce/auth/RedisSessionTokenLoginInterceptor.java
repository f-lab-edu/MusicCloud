package com.flab.livecommerce.auth;

import static com.flab.common.auth.SessionConst.AUTH_SESSION_MEMBER;

import com.flab.common.auth.annotation.LoginCheck;
import com.flab.common.exception.AuthenticationException;
import com.flab.user.domain.TokenRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class RedisSessionTokenLoginInterceptor implements HandlerInterceptor {

    private final TokenRepository tokenRepository;

    public RedisSessionTokenLoginInterceptor(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        //token 정보 가져오기
        var token = extractToken(request);

        if (token != null) {
            var authenticatedUser = tokenRepository.findByToken(token);

            if (authenticatedUser == null) {
                throw new AuthenticationException();
            }

            tokenRepository.renewExpirationSec(authenticatedUser);

            //request 에 세션정보 담기
            request.setAttribute(AUTH_SESSION_MEMBER, authenticatedUser);
        }

        return true;
    }

    private String extractToken(HttpServletRequest request) {
        var token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token != null && token.startsWith("Bearer")) {
            return token.replace("Bearer ", "");
        }

        return null;
    }
}
