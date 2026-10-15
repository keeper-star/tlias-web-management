package com.itheima.filter;

import com.itheima.utils.CurrentHolder;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override  //request 和 response 就是当前请求和响应对象 chain表示过滤器链
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        //1.获取到请求路径
        String requestURI = httpRequest.getRequestURI(); // 获取请求路径

        //2.判断是否是登录请求，如果路径中包含 /login，说明是登录操作，放行
        if (requestURI.contains("/login")) {
            log.info("登录请求，放行{}", requestURI);
            chain.doFilter(request, response);
            return;
        }
        //3.如果不是登录请求,获取请求头中的token
        String token = httpRequest.getHeader("token");
        log.info("获取到的token: {}", token);

        //4.判断token是否存在，如果不存在，说明没有登录，返回401状态码
        if (token == null || token.isEmpty()) {
            log.info("token不存在,响应401状态码");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //5.如果token存在，校验令牌，如果校验失败 -> 返回错误信息（返回401状态码）
        try {
            Claims claims = JwtUtils.parseJWT(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("当前请求的员工ID: {},将其存入当前线程的ThreadLocal中", empId);

        } catch (Exception e) {
            log.info("token校验失败,响应401状态码");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //6.校验通过，放行
        log.info("token校验通过,放行");
        chain.doFilter(request, response);

        //7.移除ThreadLocal中的数据
        CurrentHolder.remove();
    }
}
