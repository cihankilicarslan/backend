package com.keral.inventoryManagementSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(authorize ->
                        authorize
                                .antMatchers("/UserApi/register/**", "/UserApi/index").permitAll()
                                .antMatchers("/UserApi/users").hasRole("ADMIN")
                                .antMatchers("/inventory/products").hasRole("ADMIN")
                                .antMatchers("/reports/generate").hasRole("ADMIN")
                                .antMatchers(HttpMethod.POST,"/sales/**").hasRole("ADMIN")
                                .antMatchers(HttpMethod.POST,"sales/delete/").hasRole("ADMIN")
                                .antMatchers(HttpMethod.POST,"/inventory/save").hasRole("ADMIN")
                                 .antMatchers(HttpMethod.POST, "/inventory/update/**").hasRole("ADMIN")
                                .antMatchers(HttpMethod.POST, "/inventory/delete/**").hasRole("ADMIN")





                )
                .exceptionHandling().accessDeniedPage("/error") // Erişim reddedildiğinde özel hata sayfası
                .and()
                .formLogin(form -> form
                        .loginPage("/UserApi/login")
                        .loginProcessingUrl("/UserApi/login")
                        .defaultSuccessUrl("/UserApi/users")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/UserApi/logout-success")
                        .permitAll()
                );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}