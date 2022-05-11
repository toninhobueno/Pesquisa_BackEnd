package com.bueno.PesquisaTopicos.interfaceadapter.controller;

import com.bueno.PesquisaTopicos.domain.Topico;
import com.bueno.PesquisaTopicos.domain.UserSystem;
import com.bueno.PesquisaTopicos.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/pesquisa")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;


    @GetMapping
    List<Topico> vizualiza(@AuthenticationPrincipal UserDetails userDetails) {
        return topicoService.vizualiza((UserSystem) userDetails);
    }

    @GetMapping("/all")
    List<Topico> vizualizaTodos(@AuthenticationPrincipal UserDetails userDetails) {
        return topicoService.vizualiza();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Topico> cadastra(@RequestBody @Valid Topico topico, UriComponentsBuilder uriBuilder,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        topicoService.salvar(topico, userDetails);
        URI uri = uriBuilder.path("/pesquisa/{id}").buildAndExpand(topico.getIdTopico()).toUri();
        return ResponseEntity.created(uri).body(topico);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return topicoService.deletar(id);
    }

}
