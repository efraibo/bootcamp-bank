package com.zup.bootcamp.bootcampbank.service;

import com.zup.bootcamp.bootcampbank.DTO.ClienteDto;
import com.zup.bootcamp.bootcampbank.entities.Cliente;
import com.zup.bootcamp.bootcampbank.mapper.ClienteMapper;
import com.zup.bootcamp.bootcampbank.repositories.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public ClienteDto salvarCliente(ClienteDto clienteDto) {
        if (clienteRepository.existsByEmail(clienteDto.getEmail()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        final Cliente cliente = clienteRepository.saveAndFlush(clienteMapper.dtoToDomain(clienteDto));
        return clienteMapper.domainToDto(cliente);

    }

    public List<ClienteDto> listarTodosClientes() {
        final List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toClintesDto(clientes);
    }
}
