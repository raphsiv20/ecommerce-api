package com.ecommerce.api.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${auth0.audience}")
    private String audience;

    @Value("${spring.security.oauth2.resource-server.jwt.issuer-uri}")
    private String issuer;

    @Value("${spring.security.oauth2.resource-server.jwt.jwk-set-uri}")
    private String issuerUri;


    private static final String[] AUTH_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v1/orders/renderPdf/**",
            "/v1/products/**",
            "/v1/products/cat/**",
            "/static/images/**",
    };

    private static final String[] AUTH_LIST_GET = {
            "v1/orders/allOrders/**",
            "v1/orders/anOrder/**",
            "v1/orders/generatePdf/**",
            "v1/orders/user/**",
            "/v1/products/stockProduct/**",
    };

    private static final String[] AUTH_LIST_POST = {
            "/v1/products/**",
            "v1/orders/**",
    };

    private static final String[] AUTH_LIST_PUT = {
            "/v1/products/**",
            "v1/orders/**"
    };

    private static final String[] AUTH_LIST_DELETE = {
            "/v1/products/**",
            "v1/orders/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint())
            .and()
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers( "/v1/users/**", "/v1/carts/**", "/v1/cartItems/**", "v1/shippings/**", "v1/orderItems/**").authenticated()
                    .requestMatchers(HttpMethod.GET, AUTH_WHITELIST).permitAll()
                    .requestMatchers(HttpMethod.GET, AUTH_LIST_GET).authenticated()
                    .requestMatchers(HttpMethod.POST, AUTH_LIST_POST).authenticated()
                    .requestMatchers(HttpMethod.PUT, AUTH_LIST_PUT).authenticated()
                    .requestMatchers(HttpMethod.DELETE, AUTH_LIST_DELETE).authenticated()
            )
            .cors()
            .and()
            .oauth2ResourceServer().jwt().decoder(jwtDecoder())
        ;
        return http.build();
    }


    private AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationFailureHandler();
    }



    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(issuerUri).jwsAlgorithm(SignatureAlgorithm.RS256).build();
    }





}