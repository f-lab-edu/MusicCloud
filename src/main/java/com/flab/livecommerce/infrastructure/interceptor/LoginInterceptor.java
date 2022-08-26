package com.flab.livecommerce.infrastructure.interceptor;

import com.flab.livecommerce.application.facade.UserTokenManager;
import com.flab.livecommerce.domain.exception.user.UnauthenticatedUserException;
import com.flab.livecommerce.infrastructure.annotation.LoginCheck;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


public class LoginInterceptor implements HandlerInterceptor {

    private final UserTokenManager userTokenManager;

    public LoginInterceptor(UserTokenManager userTokenManager) {
        this.userTokenManager = userTokenManager;
    }

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            LoginCheck loginCheck = method.getMethodAnnotation(LoginCheck.class);

            if (null == loginCheck) {
                return true;
            }

            String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (hasNoAuthToken(tokenHeader) || hasNoLoginInfo(tokenHeader)) {
                throw new UnauthenticatedUserException();
            }
        }
        return true;
    }

    private boolean hasNoLoginInfo(String tokenHeader) {
        String token = tokenHeader.replace("Bearer ", "");
        return null == userTokenManager.getLoginUserInfo(token);
    }

    private boolean hasNoAuthToken(String tokenHeader) {
        return null == tokenHeader || !tokenHeader.startsWith("Bearer");
    }
}
