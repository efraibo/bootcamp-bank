package com.zup.bootcamp.DTO;

import com.zup.bootcamp.DTO.validator.MaiorIdade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDto {

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @NotEmpty(message = "{campo.sobrenome.obrigatorio}")
    private String sobrenome;

    @Email(message = "{campo.email.invalido}")
    private String email;

    @NotNull(message = "{campo.data-nascimento.obrigatorio}")
    @MaiorIdade(message = "{campo.maior-idade.obrigatorio}")
    private LocalDate dataNascimento;

    @CPF(message = "{campo.cpf.invalido}")
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    private String cpf;
}
