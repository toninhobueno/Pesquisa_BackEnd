package com.bueno.PesquisaTopicos.service;


import com.bueno.PesquisaTopicos.domain.Topico;
import com.bueno.PesquisaTopicos.domain.UserSystem;
import com.bueno.PesquisaTopicos.repository.TopicoRepository;
import com.bueno.PesquisaTopicos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Topico> vizualiza(UserSystem autor) {
        return topicoRepository.findByAutor(autor);
    }

    public List<Topico> vizualiza() {
        return topicoRepository.findAll();
    }

    public void salvar(Topico topico, UserDetails userDetails) {
        topico.setAutor(userRepository.findByEmail((userDetails.getUsername())));
        topicoRepository.save(topico);
    }

    public ResponseEntity<?> deletar(Long id) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
