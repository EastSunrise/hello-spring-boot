package cn.wsg.springboot.pojo;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class LoginUser {

    private static final List<GrantedAuthority> ADMIN_AUTHORITIES = List.of(new SimpleGrantedAuthority("admin"));
    private static final List<GrantedAuthority> TEST_AUTHORITIES = List.of(new SimpleGrantedAuthority("test"));

    private final int userId;
    private final boolean admin;

    public LoginUser(int userId, boolean admin) {
        this.userId = userId;
        this.admin = admin;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (admin) {
            return ADMIN_AUTHORITIES;
        }
        return TEST_AUTHORITIES;
    }
}
