package com.marcosbrito.compass.spring.security.demo.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.marcosbrito.compass.spring.security.demo.repository.PessoaRepository;
import com.marcosbrito.compass.spring.security.demo.web.token.security.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final PessoaRepository pessoaRepository;

    // Para toda requisição, essa classe é chamada
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = this.recorverToken(request);

        if (token != null) {
            var login = tokenService.validadeToken(token);
            UserDetails userDetails = pessoaRepository.findByEmail(login);

            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            // Passando o token confirmado pro contexto spring
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Chama o proximo filtro
        filterChain.doFilter(request, response);
    }

    private String recorverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            return null;
        } // Verifica se o meu header possui algum token, caso não, ele retorna null

        return authHeader.replace("Bearer ", ""); // Limpando o token que vem no cabeçalho. Desssa forma eu retorno o
                                                  // token puro sem prefixos
    }

}
