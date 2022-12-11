package cn.wsg.springboot.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("customExpressionRoot")
public class CustomExpressionRoot {

    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getDetails();
        return false;
    }
}
