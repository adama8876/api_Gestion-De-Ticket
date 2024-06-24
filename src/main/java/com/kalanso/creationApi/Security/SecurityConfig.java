package com.kalanso.creationApi.Security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UtilisateurInfoUserDetailService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Désactiver CSRF pour simplifier les tests API
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login", "/register").permitAll() // Permettre l'accès aux routes de connexion et d'inscription
                                .requestMatchers("/api/admins/**").hasRole("ADMIN")
                                .requestMatchers("/formateurs/**").permitAll()
                                //.requestMatchers("/formateur/**").permitAll()
                                .requestMatchers("/apprenant/**").hasRole("APPRENANT")
                                .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .formLogin(formLogin ->
                        formLogin
                                .loginProcessingUrl("/perform_login")
                                .successHandler(authenticationSuccessHandler())
                                .failureHandler(authenticationFailureHandler())
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/perform_logout")
                                .logoutSuccessHandler((request, response, authentication) -> {
                                    response.getWriter().println("Déconnexion réussie");
                                    response.setStatus(HttpServletResponse.SC_OK);
                                })
                                .permitAll()
                )
                .authenticationProvider(authenticationProvider());

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            response.getWriter().println("Connexion OK");
            response.setStatus(HttpServletResponse.SC_OK);
        };
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            response.getWriter().println("Erreur de connexion: " + exception.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        };
    }

}
