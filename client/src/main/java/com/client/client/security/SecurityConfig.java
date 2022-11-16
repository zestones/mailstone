package com.client.client.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Deprecated
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/index", "/**", "/index/**", "/css/**", "/js/**", "/img/**", "/img/icon/**",
                        "/h2-console/**")
                .permitAll();

        // ! Allow access to the h2-console
        http.authorizeRequests().antMatchers("/h2-console", "/h2-console/**");
        http.csrf().ignoringAntMatchers("/h2-console", "/h2-console/**", "/api/**");
        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests().anyRequest().denyAll();
    }

}
