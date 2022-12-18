package cn.wsg.springboot.config;

import cn.wsg.springboot.common.JwtUtil;
import cn.wsg.springboot.pojo.dto.LoggedUserDTO;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter to authenticate the JSON Web Token.
 *
 * @author Kingen
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        LoggedUserDTO user = JwtUtil.parseToken(token);
        if (user == null) {
            filterChain.doFilter(request, response);
            return;
        }
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserId(), null,
            user.getScope().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
