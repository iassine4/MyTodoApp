package fr.fms.mytodoapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration de sécurité de l'application.
 */
@Configuration
public class SecurityConfig {

    /**
     * Définit les règles d'accès HTTP.
     *
     * @param http objet de configuration HttpSecurity
     * @return la chaîne de filtres de sécurité
     * @throws Exception en cas d'erreur de configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Pages publiques
                        .requestMatchers("/", "/home", "/css/**", "/js/**", "/images/**").permitAll()
                        // Tout le reste nécessite une connexion
                        .anyRequest().authenticated()
                )
                // Formulaire de login par défaut de Spring Security
                .formLogin(Customizer.withDefaults());

        return http.build();
    }
}