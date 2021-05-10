package com.sda.javaremote18.spring_boot.security;

import com.sda.javaremote18.spring_boot.controllers.UsersRepository;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityData extends WebSecurityConfigurerAdapter {

    private final UsersRepository userRepo;

    public SecurityData(UsersRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> userRepo
                .findByEmail(username));
    }

    // Details omitted for brevity

}
