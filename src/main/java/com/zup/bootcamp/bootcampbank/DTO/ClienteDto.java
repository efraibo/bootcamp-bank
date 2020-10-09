package com.zup.bootcamp.bootcampbank.DTO;

import com.zup.bootcamp.bootcampbank.DTO.validator.CpfConstraint;
import com.zup.bootcamp.bootcampbank.DTO.validator.MaiorIdadeConstraint;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class ClienteDto {

    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;

    @NotBlank(message = "O campo sobrenome é obrigatório")
    private String sobrenome;

    @Email(message = "Este email já foi cadastrado")
    private String email;

    @NotNull(message = "O campo data de nascimento é obrigatório")
    @Past
    @MaiorIdadeConstraint(message = "O cliente não pode ter menos que 18 anos")
    private LocalDate dataNascimento;

    @CpfConstraint(message = "Cpf inválido")
    @NotBlank(message = "O campo cpf é obrigatório")
    @Size(min = 11, max = 11)
    private String cpf;
}
