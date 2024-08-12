package com.example.demo.security;

import com.example.demo.service.UserDetailsService;
import com.example.demo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//Enables Spring Security for your application.
@Configuration
public class DemoConfig {


    private UserDetailsService userDetailsService;


    @Autowired
    public DemoConfig(@Lazy UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer->
                configurer

                        .requestMatchers(HttpMethod.GET ,"/products/**" ).hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/transaction/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,"/category/**").hasRole("USER")
                        .anyRequest().hasRole("ADMIN")
                       // .anyRequest().authenticated()

        )
             //   .formLogin(form -> form
             //           .permitAll()
             //   )
             //   .logout(logout -> logout
            //            .permitAll()
                //);
        ;

        //use Http  Basic Auth
        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf-> csrf.disable());

        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
