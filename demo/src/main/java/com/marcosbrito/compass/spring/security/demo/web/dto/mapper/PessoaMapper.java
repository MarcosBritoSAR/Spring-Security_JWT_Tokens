package com.marcosbrito.compass.spring.security.demo.web.dto.mapper;


import org.modelmapper.ModelMapper;
import com.marcosbrito.compass.spring.security.demo.entites.Pessoa;
import com.marcosbrito.compass.spring.security.demo.web.dto.CriarPessoaDto;
import com.marcosbrito.compass.spring.security.demo.web.dto.ResponsePessoaDto;

public class PessoaMapper {

    public static Pessoa toPessoa(CriarPessoaDto pessoaDto) {
        return new ModelMapper().map(pessoaDto, Pessoa.class);
    }

    public static ResponsePessoaDto toDto(Pessoa pessoa) {
        return new ModelMapper().map(pessoa, ResponsePessoaDto.class);
    }

}
