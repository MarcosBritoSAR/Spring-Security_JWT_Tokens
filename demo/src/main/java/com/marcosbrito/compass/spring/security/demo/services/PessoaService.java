package com.marcosbrito.compass.spring.security.demo.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.marcosbrito.compass.spring.security.demo.entites.Pessoa;
import com.marcosbrito.compass.spring.security.demo.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaService {


    private final PessoaRepository pessoaRepository;

    @Transactional(readOnly = true)

    public List<Pessoa> getAll() {

        return pessoaRepository.findAll();
    }

    public Pessoa getById(Long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> {throw new RuntimeException("Pessoa não encontrada");});
    }

}
