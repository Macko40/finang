package com.maykelmarrero.springboot.finangportal.security;

import com.maykelmarrero.springboot.finangportal.controller.CustomAuthenticationSuccessHandler;
import com.maykelmarrero.springboot.finangportal.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
public class SecurityConfig {
    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

/*    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("USER")
                .build();

        UserDetails maria = User.builder()
                .username("maria")
                .password("{noop}test123")
                .roles("CALLCENTER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(john, maria, susan);

    }*/


    //bcrypt bean definition
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //AuthenticationProvider bean definition
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService usersService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usersService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }


    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandlerBean() {
        return new CustomAuthenticationSuccessHandler(userService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(antMatcher("/kreditangeboten/veroeffentlichtenKreditangebote"),
                                        antMatcher("/img/**"), antMatcher("/kreditangebotantrag/formular**"),
                                        antMatcher("/kreditangebotantrag/save"),
                                        antMatcher("/register/**"),
                                        antMatcher("/style.css"))
                                .permitAll() // Public access
                                .requestMatchers(antMatcher("/kreditangeboten/**"),
                                        antMatcher("/kreditangebotantrag/**"),
                                        antMatcher("/img/**"),
                                        antMatcher("/style.css"))
                                .hasRole("ADMIN") // Admin access
                                .requestMatchers(antMatcher("/kreditangebotantrag/**"),
                                        antMatcher("/img/**"),
                                        antMatcher("/style.css"))
                                .hasRole("CALLCENTER") // Callcenter access
                                .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form

                                .loginPage("/login")
                                .loginProcessingUrl("/authenticateUser")
                                .successHandler(customAuthenticationSuccessHandlerBean())
                                .permitAll()
                )

                .logout(logout -> logout.permitAll()
                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
                );

        return http.build();
    }

}
