package com.zup.bootcamp.service;

import com.zup.bootcamp.DTO.ClienteDto;
import com.zup.bootcamp.entities.Cliente;
import com.zup.bootcamp.mapper.ClienteMapper;
import com.zup.bootcamp.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteDto salvarCliente(ClienteDto clienteDto) {
//        if (clienteRepository.existsByEmail(clienteDto.getEmail()))
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        final Cliente cliente = clienteRepository.saveAndFlush(clienteMapper.dtoToDomain(clienteDto));
        return clienteMapper.domainToDto(cliente);

    }

    public List<ClienteDto> listarTodosClientes() {
        final List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toClintesDto(clientes);
    }
}
