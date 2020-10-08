package com.zup.bootcamp.bootcampbank.entities;

import com.zup.bootcamp.bootcampbank.entities.validator.MaiorIdade;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
public class Cliente extends ObjetoPersistente{

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Column(nullable = false, unique = true)
    private String email;

    @Size(min = 11, max = 11)
    @Column(nullable = false, length = 11)
    private String cnh;

    @MaiorIdade
    @Past
    private LocalDate dataNascimento;


    @Size(min = 11, max = 11)
    @Column(unique = true, length = 11)
    private String cpf;
}
