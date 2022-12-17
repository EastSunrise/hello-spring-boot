package cn.wsg.springboot.pojo;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

public enum UserScope {
    ADMIN, USER, CLOUD;

    public Collection<GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(name());
    }
}
