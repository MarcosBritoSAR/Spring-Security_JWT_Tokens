package com.marcosbrito.compass.spring.security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.marcosbrito.compass.spring.security.demo.entites.Pessoa;

public interface PessoaRepository  extends JpaRepository<Pessoa, Long> {

    UserDetails findByEmail(String username);
    
}
