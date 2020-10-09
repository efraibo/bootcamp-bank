package com.zup.bootcamp.bootcampbank.controllers;

import com.zup.bootcamp.bootcampbank.DTO.ClienteDto;
import com.zup.bootcamp.bootcampbank.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDto> listarTodosClientes() {
        return clienteService.listarTodosClientes();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto casdastrarCliente(@Valid @RequestBody ClienteDto clienteDto) {
        ClienteDto response = clienteService.salvarCliente(clienteDto);
        return response;
    }
}
