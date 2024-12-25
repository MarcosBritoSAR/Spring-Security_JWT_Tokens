package com.marcosbrito.compass.spring.security.demo.services;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.marcosbrito.compass.spring.security.demo.repository.PessoaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PessoaDetailsService implements UserDetailsService {

    private final PessoaRepository pessoaRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return pessoaRepository.findByEmail(username);
    }

    
    
}
