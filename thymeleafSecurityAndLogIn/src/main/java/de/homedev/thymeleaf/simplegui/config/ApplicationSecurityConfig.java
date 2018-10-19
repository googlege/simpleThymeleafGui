package de.homedev.thymeleaf.simplegui.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import de.homedev.thymeleaf.simplegui.service.api.IUserService;
import de.homedev.thymeleaf.simplegui.util.AppUtil;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String[] RESOURCE_BACKEND_MATCHERS = new String[] {
            "/css/**", "/js/**", "/webjars/**", "/fonts/**", "/images/**"
    };
    public static final String FILES_MATCHER = "/files/**";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(RESOURCE_BACKEND_MATCHERS).permitAll().anyRequest().fullyAuthenticated().and().formLogin()
            .loginPage("/login")
            .failureUrl("/login?error").permitAll().and().logout().permitAll();
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder auth, IUserService userService)
        throws Exception {
        auth.userDetailsService(userService).passwordEncoder(AppUtil.getPasswordEncoder());
    }

}
