package com.zup.bootcamp.bootcampbank.controllers;

import com.zup.bootcamp.bootcampbank.entities.Cliente;
import com.zup.bootcamp.bootcampbank.repositories.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> listarTodosClientes(){
        return clienteRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente casdastrarCliente(@Valid @RequestBody Cliente cliente) {
        return clienteRepository.saveAndFlush(cliente);
    }
}
