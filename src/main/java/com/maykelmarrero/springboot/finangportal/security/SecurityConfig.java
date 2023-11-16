package com.maykelmarrero.springboot.finangportal.security;

import com.maykelmarrero.springboot.finangportal.controller.CustomAuthenticationSuccessHandler;
import com.maykelmarrero.springboot.finangportal.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
                                .requestMatchers("/kreditangeboten/veroeffentlichtenKreditangebote").permitAll() // Public access
                                .requestMatchers("/kreditangeboten/**", "/kreditangebotantrag/**").hasRole("ADMIN") // Admin access
                                .requestMatchers("/kreditangebotantrag/**").hasRole("CALLCENTER") // Callcenter access
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
