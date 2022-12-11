package cn.wsg.springboot.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("authenticationEntryPoint")
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    /**
     * todo handle exceptions when failing to authenticate, 401
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // todo write responseResult to response
    }
}
