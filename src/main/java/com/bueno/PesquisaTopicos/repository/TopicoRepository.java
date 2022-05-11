package com.bueno.PesquisaTopicos.repository;

import com.bueno.PesquisaTopicos.domain.Topico;
import com.bueno.PesquisaTopicos.domain.UserSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    List<Topico> findByAutor(UserSystem autor);

}
