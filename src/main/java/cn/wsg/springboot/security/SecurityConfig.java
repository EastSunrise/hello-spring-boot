package cn.wsg.springboot.security;

import cn.wsg.springboot.pojo.UserScope;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Resource(name = "jwtTokenFilter")
    private JwtTokenFilter jwtTokenFilter;
    @Resource(name = "authenticationEntryPoint")
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Resource(name = "accessDeniedHandler")
    private AccessDeniedHandler accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests().antMatchers("/auth/**").anonymous()
            .antMatchers("/cloud/api/root").anonymous()
            .antMatchers("/admin/**").hasAuthority(UserScope.ADMIN.name())
            .antMatchers("/user/**").hasAuthority(UserScope.USER.name())
            .antMatchers("/cloud/api/**").denyAll()
            .anyRequest().denyAll();
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
            .accessDeniedHandler(accessDeniedHandler);
        http.cors();
        return http.build();
    }
}
