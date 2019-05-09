package com.pfs.riskmodel.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@Configuration
@Profile({ "oauth","pfsdevoauth"})
//@Profile("pfsdevoauth")


// For Production
//@Profile("pfsprdoauth")


// - Activate this for development
//@Profile("oauth")

@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .antMatcher("/**").authorizeRequests()
                .antMatchers( "/api/riskModel", "/login**", "/webjars/**", "/error**").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable().headers().frameOptions().disable();
    }
}