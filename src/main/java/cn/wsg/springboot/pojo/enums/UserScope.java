package cn.wsg.springboot.pojo.enums;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Roles of users.
 *
 * @author Kingen
 */
public enum UserScope {
    ADMIN, USER, CLOUD;

    public Collection<GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(name());
    }
}
