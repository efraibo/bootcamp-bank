package com.zup.bootcamp.bootcampbank.controllers;

import com.zup.bootcamp.bootcampbank.DTO.ClienteDto;
import com.zup.bootcamp.bootcampbank.entities.Cliente;
import com.zup.bootcamp.bootcampbank.mapper.ClienteMapper;
import com.zup.bootcamp.bootcampbank.service.ClienteService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/clientes")
@Api("Api Clientes")
public class ClienteController {

    private final ClienteService clienteService;
//    private final ClienteMapper clienteMapper;

    @GetMapping
    @ApiOperation("Obter todos os clientes")
    public List<ClienteDto> listarTodosClientes() {
        return clienteService.listarTodosClientes();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente cadastrado com sucesso."),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    @PostMapping
    @ApiOperation("Cadastrar clientes")
    public ClienteDto casdastrarCliente(@Valid @RequestBody @ApiParam("Cliente") ClienteDto clienteDto) {
        ClienteDto response = clienteService.salvarCliente(clienteDto);
        return response;
    }
}
