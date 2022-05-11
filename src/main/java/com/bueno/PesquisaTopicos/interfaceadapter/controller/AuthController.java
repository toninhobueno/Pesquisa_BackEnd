package com.bueno.PesquisaTopicos.interfaceadapter.controller;

import com.bueno.PesquisaTopicos.domain.Topico;
import com.bueno.PesquisaTopicos.domain.UserSystem;
import com.bueno.PesquisaTopicos.repository.TopicoRepository;
import com.bueno.PesquisaTopicos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/")
public class AuthController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicoRepository topicoRepository;


    @PostMapping
    @Transactional
    public ResponseEntity<HttpStatus> vizualizar() {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Topico> chamarDados(@AuthenticationPrincipal UserSystem autor) {
        return topicoRepository.findByAutor(autor);
    }

}
