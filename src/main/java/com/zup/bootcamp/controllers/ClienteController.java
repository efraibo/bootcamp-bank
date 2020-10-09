package com.zup.bootcamp.controllers;

import com.zup.bootcamp.DTO.ClienteDto;
import com.zup.bootcamp.entities.Cliente;
import com.zup.bootcamp.mapper.ClienteMapper;
import com.zup.bootcamp.service.ClienteService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/clientes")
@Api("Api Clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    @GetMapping
    @ApiOperation("Obter todos os clientes")
    public List<ClienteDto> listarTodosClientes() {
        return clienteMapper.toClintesDto(clienteService.listarTodosClientes());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente cadastrado com sucesso."),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    @PostMapping
    @ApiOperation("Cadastrar clientes")
    public ClienteDto casdastrarCliente(@Valid @RequestBody @ApiParam("Cliente") ClienteDto clienteDto) {
        Cliente cliente = clienteService.salvarCliente(clienteMapper.dtoToDomain(clienteDto));
        return clienteMapper.domainToDto(cliente);
    }
}
