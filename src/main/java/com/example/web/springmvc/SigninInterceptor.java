package com.example.web.springmvc;

import com.example.web.HttpUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class SigninInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
                             Object handler) throws Exception {
        HttpSession session = req.getSession();
        Object userId = session.getAttribute("me_userId");
        if (userId != null) // 로그인 한 경우 그대로 진행
            return true;

        // 로그인 안한 경우 로그인 화면으로
        String redirectUrl = HttpUtils.getRequestURLWithQueryString(req);
        log.debug("redirectUrl={}", redirectUrl);

        HttpUtils.redirect(req, resp,
                "/app/user/signinForm?redirectUrl=" + HttpUtils.encodeUrl(redirectUrl));
        return false;
    }
}