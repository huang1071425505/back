package com.linLing.project.utils;

import io.jsonwebtoken.*;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;


/**
 * token拦截器
 */
@Component
public class TokenInterceptorUtil implements HandlerInterceptor {
    @Value(value = "${jwt.secret}")
    private String secret;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if (token != null){
            boolean result = verify(token,secret);
            if(result){
                System.out.println("通过拦截器");
                return true;
            }
        }
        if("/sysUsers/login".equals(request.getServletPath())){
            return true;
        }
        System.out.println("认证失败");
        response.getWriter().write("50000");
        return false;
    }
    /**
     * 检验token是否正确
     * @param **token**
     * @return
     */
    private static boolean verify(String token,String secret){
        boolean check=false;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                    .parseClaimsJws(token)
                    .getBody();
            check=true;
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException("JWT 令牌过期:" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            throw new AuthenticationException("JWT 令牌无效:" + e.getMessage());
        } catch (MalformedJwtException e) {
            throw new AuthenticationException("JWT 令牌格式错误:" + e.getMessage());
        } catch (SignatureException e) {
            throw new AuthenticationException("JWT 令牌签名无效:" + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new AuthenticationException("JWT 令牌参数异常:" + e.getMessage());
        } catch (Exception e) {
            throw new AuthenticationException("JWT 令牌错误:" + e.getMessage());
        }
        return  check;
    }

}