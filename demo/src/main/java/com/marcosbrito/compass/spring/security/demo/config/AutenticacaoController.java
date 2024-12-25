package com.marcosbrito.compass.spring.security.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcosbrito.compass.spring.security.demo.entites.Pessoa;
import com.marcosbrito.compass.spring.security.demo.repository.PessoaRepository;
import com.marcosbrito.compass.spring.security.demo.web.dto.RegistroDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//Criando o endPoint para controle de autenticação
@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AutentificacaoDto dto) {

        var username = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());// Encapsula o usuario e senha
        var auth = this.authenticationManager.authenticate(username); // Verifica no banco de dados as credenciais e
                                                                      // retorna o ususairo ja autenticado

        /*
         * Estou configurando a autenticação do usuario aqui para preciso informar no
         * SSC que ele tem que buscar aqui a forma de autenticação
         */
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cadastro")
    public ResponseEntity cadastro(@RequestBody RegistroDto dto) {

        if (pessoaRepository.findByEmail(dto.getEmail()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encriptedPassword = new BCryptPasswordEncoder().encode(dto.getSenha());
        Pessoa pessoa = new Pessoa(dto.getEmail(), encriptedPassword);
        pessoaRepository.save(pessoa);
        return ResponseEntity.ok().build(); // retorno o Buildder quandoe não preciso retornar corpo algum4

    }

}
