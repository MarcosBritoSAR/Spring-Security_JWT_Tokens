package com.marcosbrito.compass.spring.security.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import com.marcosbrito.compass.spring.security.demo.common.ConstantesPessoa;
import com.marcosbrito.compass.spring.security.demo.entites.Pessoa;
import com.marcosbrito.compass.spring.security.demo.services.PessoaService;

import lombok.RequiredArgsConstructor;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {
    
    private final PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAll(){
        List<Pessoa> pessoas = pessoaService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(ConstantesPessoa.PESSOAS);
    }
    

}
