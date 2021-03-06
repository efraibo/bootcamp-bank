package com.zup.bootcamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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

    @JsonProperty
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull(message = "{campo.data-nascimento.obrigatorio}")
    @MaiorIdade(message = "{campo.data-nascimento.maior-idade}")
    private LocalDate dataNascimento;

    @CPF(message = "{campo.cpf.invalido}")
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    private String cpf;
}
