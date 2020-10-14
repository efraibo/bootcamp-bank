package com.zup.bootcamp.repository;

import com.zup.bootcamp.entities.Cliente;
import com.zup.bootcamp.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente clientePadrao = new Cliente();

    @BeforeEach
    public void init() {
        clientePadrao = Cliente.builder()
                .cpf("01234567890")
                .dataNascimento(LocalDate.now().minusYears(18))
                .nome("João")
                .sobrenome("do Teste")
                .email("teste@teste.com").build();
    }

    @Test
    @DisplayName("Deve salvar o cliente com sucesso.")
    void salvarClienteTest() {

        //given
        Cliente clienteAtual = this.clientePadrao;

        //when
        final Cliente clienteRetornado = clienteRepository.saveAndFlush(clienteAtual);

        //then
        assertThat(clienteRetornado.getId()).isNotNull();
        assertThat(clienteRetornado.getNome()).isEqualTo(clienteAtual.getNome());
        assertThat(clienteRetornado.getSobrenome()).isEqualTo(clienteAtual.getSobrenome());
        assertThat(clienteRetornado.getEmail()).isEqualTo(clienteAtual.getEmail());
        assertThat(clienteRetornado.getCpf()).isEqualTo(clienteAtual.getCpf());
        assertThat(clienteRetornado.getDataNascimento()).isEqualTo(clienteAtual.getDataNascimento());
    }

    @Test
    @DisplayName("Deve retornar todos os clientes salvos")
    void clienteFindAllTest() {
        //given
        final Cliente cliente1 = Cliente.builder()
                .cpf("000000000191")
                .dataNascimento(LocalDate.now().minusYears(18))
                .nome("Maria")
                .sobrenome("do Teste")
                .email("teste1@teste.com").build();

        clienteRepository.saveAndFlush(clientePadrao);
        clienteRepository.saveAndFlush(cliente1);


        //when
        final List<Cliente> clientesRetornado = clienteRepository.findAll();

        //then
        assertThat(clientesRetornado).isNotNull();
        assertThat(clientesRetornado.size()).isEqualTo(2);
        assertThat(clientesRetornado.get(1).getNome()).isEqualTo(cliente1.getNome());

    }

    @Test
    @DisplayName("Deve verificar se o email consultado já existe na base de dados")
    void emailExistenteTest() {
        //given
        final Cliente cliente = clienteRepository.saveAndFlush(clientePadrao);

        //when
        final boolean jaExisteEmailCadastrado = clienteRepository.existsByEmail(cliente.getEmail());

        //then
        assertThat(jaExisteEmailCadastrado).isTrue();
    }
}
