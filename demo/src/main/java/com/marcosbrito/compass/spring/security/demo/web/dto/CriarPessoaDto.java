package com.marcosbrito.compass.spring.security.demo.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarPessoaDto {
    
    @NotBlank
    @Size(min = 3, max = 50)
    private String nome;
    @NotBlank
    @Size(min = 3, max = 50)
    private String email;
    @NotBlank
    @Size(min = 6, max = 6)
    private String senha;
    
}
