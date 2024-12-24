package com.marcosbrito.compass.spring.security.demo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePessoaDto {
    private Long id;
    private String email;
}
