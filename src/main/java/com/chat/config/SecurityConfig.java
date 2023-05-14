package com.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 입력받은 비밀번호를 {algorithm} 방식으로 암호화해서 마치 DB에 보관되는 것과 유사 (현재는 Noop이므로 암호화를 하지 않음)
        auth.inMemoryAuthentication()
                .withUser("user1").password("{noop}1234").roles("USER").and()
                .withUser("admin").password("{noop}1234").roles("ADMIN");
    }
}
