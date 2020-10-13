package com.zup.bootcamp.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zup.bootcamp.validations.EmailUnico;
import com.zup.bootcamp.validations.MaiorIdade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ClienteDto {

    private Long id;

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @NotEmpty(message = "{campo.sobrenome.obrigatorio}")
    private String sobrenome;

    @EmailUnico(message = "{campo.email.unico}")
    @Email(message = "{campo.email.invalido}")
    private String email;

    @NotNull(message = "{campo.data-nascimento.obrigatorio}")
    @MaiorIdade(message = "{campo.data-nascimento.maior-idade}")
    private LocalDate dataNascimento;

    @CPF(message = "{campo.cpf.invalido}")
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    private String cpf;
}
