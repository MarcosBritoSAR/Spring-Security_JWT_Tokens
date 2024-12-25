package com.marcosbrito.compass.spring.security.demo.web.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDto{
    private String email;
    private String senha;
}

