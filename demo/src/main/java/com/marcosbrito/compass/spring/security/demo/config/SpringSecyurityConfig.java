package com.marcosbrito.compass.spring.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity //habilitando a configuração de segurança do webSecurity
public class SpringSecyurityConfig {

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
        .csrf(csrf->csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
        .requestMatchers(HttpMethod.POST, "auth/login").permitAll()
        .requestMatchers(HttpMethod.POST, "auth/cadastro").permitAll() //Eu preciso mudar isso para somente pessoas com adm poassam cadastrar
        .requestMatchers(HttpMethod.GET, "/pessoas").hasRole("ADMIN")
        .anyRequest().authenticated())
        // Desejo criar um filtro pra verificar os tokens antes do filtro padrão. o filtro padrão é o Filtro que intercepta requisições de login, extrai credenciais, autentica o usuário e lida com sucesso ou falha na autenticação.
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build(); 
            
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ //Algoritmo de Criptografia
       return new BCryptPasswordEncoder();
        
    };
}
