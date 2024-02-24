package com.estudou.discoveryserver.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class SecurityConfig {
//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//         httpSecurity
//             .securityMatcher("/eureka/**")
//             .headers((headers) ->
//                 headers.disable()
//             );

//         return httpSecurity.build();
//     }
// }
