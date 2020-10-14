package com.zup.bootcamp.services;

import com.google.common.collect.Lists;
import com.zup.bootcamp.dto.ClienteDto;
import com.zup.bootcamp.utils.ListMapperUtils;
import com.zup.bootcamp.entities.Cliente;
import com.zup.bootcamp.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteDto salvarCliente(ClienteDto dto) {
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(dto, cliente);
        cliente = clienteRepository.saveAndFlush(cliente);
        ClienteDto clienteSalvo = new ClienteDto();
        BeanUtils.copyProperties(cliente, clienteSalvo);
        return clienteSalvo;
    }

    public List<ClienteDto> listarTodosClientes() {
        ListMapperUtils<ClienteDto> utils = BeanUtils.instantiateClass(ListMapperUtils.class);
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDto> userInfoList = Lists.newArrayList();
        utils.copyList(clientes, userInfoList, ClienteDto.class);
        return userInfoList;
    }

    public boolean isExisteEmail(String email) {
        return clienteRepository.existsByEmail(email);
    }
}
