package com.chatapp.chat.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final Jwtfilter jwtfilter;
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        configuration.setAllowedHeaders(
                Arrays.asList(
                        HttpHeaders.ORIGIN,
                        HttpHeaders.CONTENT_TYPE,
                        HttpHeaders.ACCEPT,
                        HttpHeaders.AUTHORIZATION
                )
        );

        configuration.setAllowedMethods(
                Arrays.asList(
                        "GET",
                        "POST",
                        "DELETE",
                        "PUT",
                        "PATCH"
                )
        );

        source.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(source);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        request -> request.requestMatchers(
                                "/auth/**",
                                        "/swagger-resources",
                                        "/swagger-resources/**",
                                        "/configuration/ui",
                                        "/configuration/security",
                                        "/swagger-ui/**",
                                        "/webjars/**",
                                        "/swagger-ui.html",
                                        "/ws/**"
                        ).permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(
                        session -> session.sessionCreationPolicy(STATELESS)
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);

         return http.build();
    }

}
