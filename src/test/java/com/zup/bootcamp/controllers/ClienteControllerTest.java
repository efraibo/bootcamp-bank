package com.zup.bootcamp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.bootcamp.dto.ClienteDto;
import com.zup.bootcamp.entities.Cliente;
import com.zup.bootcamp.repositories.ClienteRepository;
import com.zup.bootcamp.services.ClienteService;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ClienteControllerTest {

    static String CLIENTE_API = "/api/clientes";

    ClienteDto clienteDto = new ClienteDto();

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService service;

    @Autowired
    private ClienteRepository repository;

    @BeforeEach
    public void setup() {
        BeanUtils.copyProperties(criarClientePadrao(), clienteDto);
    }

    @Test
    @DisplayName("Deve cadastrar um cliente com sucesso.")
    void salvarClienteTest() throws Exception {
        //given
        BDDMockito.when(service.salvarCliente(clienteDto)).thenReturn(clienteDto);
        String json = new ObjectMapper().writeValueAsString(clienteDto);

        //when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(CLIENTE_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        //then
        mockMvc
                .perform(request)
                .andExpect( status().isCreated() )
                .andExpect( jsonPath("id").isNotEmpty())
                .andExpect( jsonPath("nome").value(clienteDto.getNome()))
                .andExpect( jsonPath("sobrenome").value(clienteDto.getSobrenome()))
                .andExpect( jsonPath("email").value(clienteDto.getEmail()))
                .andExpect( jsonPath("dataNascimento").value(clienteDto.getDataNascimento().toString()))
                .andExpect( jsonPath("cpf").value(clienteDto.getCpf()) );
    }

    @Test
    void teste() throws Exception {
        //given
        Cliente cliente = Cliente.builder()
                .cpf("00000000191")
                .dataNascimento(LocalDate.now().minusYears(18))
                .nome("Maria")
                .sobrenome("do Teste")
                .email("teste@teste.com").build();
        repository.save(cliente);

        BDDMockito.when(service.salvarCliente(clienteDto)).thenReturn(clienteDto);

        String json = new ObjectMapper().writeValueAsString(clienteDto);

        //when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(CLIENTE_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        //then
        mockMvc.perform(request)
                .andExpect( status().isBadRequest() )
                .andExpect( jsonPath("errors", hasSize(1)));
    }

    private ClienteDto criarClientePadrao() {
        return ClienteDto.builder()
                .cpf("01234567890")
                .dataNascimento(LocalDate.now().minusYears(18))
                .nome("João")
                .sobrenome("do Teste")
                .email("teste@teste.com").build();
    }
}
