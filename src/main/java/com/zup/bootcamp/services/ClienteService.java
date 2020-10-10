package com.zup.bootcamp.services;

import com.zup.bootcamp.entities.Cliente;
import com.zup.bootcamp.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.saveAndFlush(cliente);
    }

    public List<Cliente> listarTodosClientes() {
        final List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }
}
