package com.zup.bootcamp.services;

import com.zup.bootcamp.dto.ClienteDto;
import com.zup.bootcamp.entities.Cliente;
import com.zup.bootcamp.repositories.ClienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
//@SpringBootTest
class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;
    @Mock
    private ClienteRepository clienteRepository;

    @DisplayName("Deve salvar o cliente com sucesso!")
    @Test
    void salvarCliente() {

        //given
        final ClienteDto clienteDto = clienteCriado();
        final Cliente clienteMock = clienteCriadoMock();
        when(clienteRepository.saveAndFlush(any(Cliente.class))).thenReturn(clienteMock);

        //when
        ClienteDto clienteRetornado = clienteService.salvarCliente(clienteDto);

        //then
        assertThat(clienteRetornado.getId()).isNotNull();
        assertThat(clienteRetornado.getNome()).isEqualTo(clienteDto.getNome());
        assertThat(clienteRetornado.getSobrenome()).isEqualTo(clienteDto.getSobrenome());
        assertThat(clienteRetornado.getCpf()).isEqualTo(clienteDto.getCpf());
        assertThat(clienteRetornado.getDataNascimento()).isEqualTo(clienteDto.getDataNascimento());
        assertThat(clienteRetornado.getEmail()).isEqualTo(clienteDto.getEmail());
    }

    private ClienteDto clienteCriado() {
        return ClienteDto.builder()
                .cpf("01234567890")
                .dataNascimento(LocalDate.now().minusYears(12))
                .nome("João")
                .sobrenome("do Teste")
                .email("teste@teste.com").build();
    }

    private Cliente clienteCriadoMock() {
        return Cliente.builder()
                .id(1l)
                .cpf("01234567890")
                .dataNascimento(LocalDate.now().minusYears(12))
                .nome("João")
                .sobrenome("do Teste")
                .email("teste@teste.com").build();
    }

}
