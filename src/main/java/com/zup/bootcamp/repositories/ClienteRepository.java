package com.zup.bootcamp.repositories;

import com.zup.bootcamp.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByEmail(String email);
    Collection findClienteByEmail(String email);
}
