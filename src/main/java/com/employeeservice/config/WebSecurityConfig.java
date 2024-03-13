//package com.employeeservice.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(AbstractHttpConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(
//                        auth -> auth
//                                .requestMatchers("/employee/signin").permitAll()
//                                .requestMatchers("/employee/signup").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .formLogin(Customizer.withDefaults())
//                .logout(Customizer.withDefaults());
//
//        return http.build();
//
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//}
