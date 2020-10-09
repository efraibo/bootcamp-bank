package com.zup.bootcamp.bootcampbank.mapper;

import com.zup.bootcamp.bootcampbank.DTO.ClienteDto;
import com.zup.bootcamp.bootcampbank.entities.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente dtoToDomain(final ClienteDto dto);

    ClienteDto domainToDto(final Cliente cliente);

    List<ClienteDto> toClintesDto(List<Cliente> tasks);

}
