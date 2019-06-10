package com.cxs.interceptor;

import com.cxs.redis.RedisClient;
import com.cxs.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/4/2 20:16
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisClient redis;

    @Value("http://localhost:8082/toLogin")
    private String ssoUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = CookieUtil.getCookieValue(request, "X-TOKEN");
        if (token != null && redis.isExsit(token)) {
            return true;
        } else {
            //得到当前请求的url
            StringBuffer requestURL = request.getRequestURL();
            response.sendRedirect(ssoUrl + "?lastUrl=" + requestURL);
            return false;
        }

    }

    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
