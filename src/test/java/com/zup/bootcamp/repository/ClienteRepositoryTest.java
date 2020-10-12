package com.zup.bootcamp.repository;

import com.zup.bootcamp.entities.Cliente;
import com.zup.bootcamp.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class ClienteRepositoryTest {

    Cliente clientePadrao = new Cliente();
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void init() {
        clientePadrao = Cliente.builder()
                .cpf("01234567890")
                .dataNascimento(LocalDate.now().minusYears(12))
                .nome("João")
                .sobrenome("do Teste")
                .email("teste@teste.com").build();
    }

    @Test
    @DisplayName("Deve salvar o cliente com sucesso.")
    void salvarClienteTest() {
        //given

        entityManager.persist(clientePadrao);
        entityManager.flush();

        //when
        final Cliente clienteRetornado = clienteRepository.saveAndFlush(clientePadrao);

        //then
        assertThat(clienteRetornado.getId()).isNotNull();
        assertThat(clienteRetornado.getNome()).isEqualTo(clientePadrao.getNome());
        assertThat(clienteRetornado.getSobrenome()).isEqualTo(clientePadrao.getSobrenome());
        assertThat(clienteRetornado.getEmail()).isEqualTo(clientePadrao.getEmail());
        assertThat(clienteRetornado.getCpf()).isEqualTo(clientePadrao.getCpf());
        assertThat(clienteRetornado.getDataNascimento()).isEqualTo(clientePadrao.getDataNascimento());
    }

    @Test
    @DisplayName("Deve retornar todos os clientes salvos")
    void clienteFindAllTest() {
        //given
        entityManager.persist(clientePadrao);
        entityManager.flush();

        final Cliente cliente1 = Cliente.builder()
                .cpf("000000000191")
                .dataNascimento(LocalDate.now().minusYears(12))
                .nome("Maria")
                .sobrenome("do Teste")
                .email("teste1@teste.com").build();
        entityManager.persist(cliente1);
        entityManager.flush();

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
        entityManager.persist(clientePadrao);
        entityManager.flush();
        final Cliente cliente = clienteRepository.saveAndFlush(clientePadrao);

        //when
        final boolean jaExisteEmailCadastrado = clienteRepository.existsByEmail(cliente.getEmail());

        //then
        assertThat(jaExisteEmailCadastrado).isTrue();
    }
}
