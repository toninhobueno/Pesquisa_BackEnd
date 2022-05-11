package com.bueno.PesquisaTopicos.repository;

import com.bueno.PesquisaTopicos.domain.UserSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserSystem, Long> {

    UserSystem findByEmail(String email);


}
