package com.bueno.PesquisaTopicos.interfaceadapter.controller;


import com.bueno.PesquisaTopicos.domain.UserSystem;
import com.bueno.PesquisaTopicos.repository.UserRepository;
import com.bueno.PesquisaTopicos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    @PreAuthorize("ADMIN")
    List<UserSystem> vizualiza() {
        return userService.vizualiza();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastra(@RequestBody @Valid UserSystem userSystem, UriComponentsBuilder uriBuilder) {
        Optional<UserSystem> myUser = Optional.ofNullable(userRepository.findByEmail(userSystem.getEmail()));
        if (myUser.isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        URI uri = uriBuilder.path("/api/users/{id}").buildAndExpand(userSystem.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return userService.deletar(id);
    }

}

