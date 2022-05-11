package com.bueno.PesquisaTopicos.service;


import com.bueno.PesquisaTopicos.domain.UserSystem;
import com.bueno.PesquisaTopicos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public boolean findByEmail(String email) {
        try {
            userRepository.findByEmail(email);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseEntity.notFound();
        return false;
    }

    public List<UserSystem> vizualiza() {
        return userRepository.findAll();
    }

    public void salvar(UserSystem userSystem) {
        userSystem.setSenha(passwordEncoder.encode(userSystem.getSenha()));
        userRepository.save(userSystem);
    }

    public ResponseEntity<?> deletar(Long id) {
        Optional<UserSystem> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
