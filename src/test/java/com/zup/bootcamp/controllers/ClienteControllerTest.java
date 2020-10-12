package com.zup.bootcamp.controllers;

import com.zup.bootcamp.DTO.ClienteDto;
import com.zup.bootcamp.entities.Cliente;
import com.zup.bootcamp.repositories.ClienteRepository;
import com.zup.bootcamp.services.ClienteService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ClienteControllerTest {

    static String CLIENTE_API = "/api/clientes";
    Cliente cliente = new Cliente();
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClienteService service;
    @MockBean
    private ClienteRepository repository;

    @BeforeEach
    public void setup() {
        BeanUtils.copyProperties(criarClientePadrao(), cliente);
    }

    @Test
    @DisplayName("Deve cadastrar um cliente com sucesso.")
    void salvarClienteTest() throws Exception {
        BDDMockito.when(service.salvarCliente(cliente)).thenReturn(cliente);
        ResponseEntity<Cliente> response = restTemplate.postForEntity(CLIENTE_API, criarClientePadrao(), Cliente.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
        Assertions.assertThat(response.getBody().getId()).isNotNull();
    }

    private ClienteDto criarClientePadrao() {
        return ClienteDto.builder()
                .id(1L)
                .cpf("01234567890")
                .dataNascimento(LocalDate.now().minusYears(18))
                .nome("João")
                .sobrenome("do Teste")
                .email("teste@teste.com").build();
    }
}
