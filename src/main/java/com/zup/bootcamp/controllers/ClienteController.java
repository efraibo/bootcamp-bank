package com.zup.bootcamp.controllers;

import com.google.common.collect.Lists;
import com.zup.bootcamp.DTO.ClienteDto;
import com.zup.bootcamp.Utils.ListUtils;
import com.zup.bootcamp.entities.Cliente;
import com.zup.bootcamp.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
@Api("Api Clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    @ApiOperation("Obter todos os clientes")
    public List<ClienteDto> listarTodosClientes() {
        ListUtils<ClienteDto> utils = BeanUtils.instantiateClass(ListUtils.class);
        final List<Cliente> clientes = clienteService.listarTodosClientes();
        List<ClienteDto> userInfoList = Lists.newArrayList();
        utils.copyList(clientes, userInfoList, ClienteDto.class);
        return userInfoList;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente cadastrado com sucesso."),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Cadastrar clientes")
    public ClienteDto casdastrarCliente(@Valid @RequestBody @ApiParam("Cliente") ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteDto, cliente);
        cliente = clienteService.salvarCliente(cliente);
        ClienteDto dto = new ClienteDto();
        BeanUtils.copyProperties(cliente, dto);
        return dto;
    }
}
