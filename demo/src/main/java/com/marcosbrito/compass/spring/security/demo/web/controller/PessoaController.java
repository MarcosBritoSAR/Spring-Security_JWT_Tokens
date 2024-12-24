package com.marcosbrito.compass.spring.security.demo.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import com.marcosbrito.compass.spring.security.demo.common.ConstantesPessoa;
import com.marcosbrito.compass.spring.security.demo.entites.Pessoa;
import com.marcosbrito.compass.spring.security.demo.services.PessoaService;
import com.marcosbrito.compass.spring.security.demo.web.dto.ResponsePessoaDto;
import com.marcosbrito.compass.spring.security.demo.web.dto.mapper.PessoaMapper;

import lombok.RequiredArgsConstructor;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {
    
    private final PessoaService pessoaService;


    @GetMapping("/{id}")
    public ResponseEntity<ResponsePessoaDto> getById(@PathVariable Long id){
        Pessoa pessoas = pessoaService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(PessoaMapper.toDto(pessoas));
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAll(){
        List<Pessoa> pessoas = pessoaService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(pessoas);
    }
    

}
