package com.zup.bootcamp.bootcampbank.repositories;

import com.zup.bootcamp.bootcampbank.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByEmail(String email);
}
