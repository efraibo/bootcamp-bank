package com.zup.bootcamp.services;

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

        final Cliente cliente = clienteCriado();
        final Cliente clienteMock = clienteCriadoMock();

        when(clienteRepository.saveAndFlush(any(Cliente.class))).thenReturn(clienteMock);

        Cliente clienteRetornado = clienteService.salvarCliente(cliente);

        assertThat(clienteRetornado.getId()).isNotNull();
        assertThat(clienteRetornado.getNome()).isEqualTo(cliente.getNome());
        assertThat(clienteRetornado.getSobrenome()).isEqualTo(cliente.getSobrenome());
        assertThat(clienteRetornado.getCpf()).isEqualTo(cliente.getCpf());
        assertThat(clienteRetornado.getDataNascimento()).isEqualTo(cliente.getDataNascimento());
        assertThat(clienteRetornado.getEmail()).isEqualTo(cliente.getEmail());
    }

    Cliente clienteCriado() {
        return Cliente.builder()
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
