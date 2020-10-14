package com.zup.bootcamp.controllers;

import com.zup.bootcamp.dto.ClienteDto;
import com.zup.bootcamp.Utils.ResourceUriHelper;
import com.zup.bootcamp.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@Api("Api Clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    @ApiOperation("Obter todos os clientes")
    public List<ClienteDto> listarTodosClientes() {
        return clienteService.listarTodosClientes();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente cadastrado com sucesso."),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Cadastrar clientes")
    public ClienteDto casdastrarCliente(@Valid @RequestBody @ApiParam("Cliente") ClienteDto clienteDto) {
        ClienteDto resource = clienteService.salvarCliente(clienteDto);
        ResourceUriHelper.addUriInResponseHeader(resource.getId());
        return resource;
    }
}
