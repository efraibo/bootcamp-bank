package com.zup.bootcamp.services;

import com.zup.bootcamp.DTO.ClienteDto;
import com.zup.bootcamp.entities.Cliente;
import com.zup.bootcamp.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;
    @MockBean
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("Deve salvar o cliente com sucesso!")
    @Test
    void salvarCliente() {

        final ClienteDto clienteDto = clienteCriado();
        final Cliente clienteMock = clienteCriadoMock();

        when(clienteRepository.saveAndFlush(any(Cliente.class))).thenReturn(clienteMock);

        ClienteDto clienteRetornado = clienteService.salvarCliente(clienteDto);

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
