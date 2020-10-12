package com.zup.bootcamp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Cliente extends ObjetoPersistente {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String cpf;

    @Builder
    public Cliente(Long id, String nome, String sobrenome, String email, LocalDate dataNascimento, String cpf) {
        super(id);
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }
}
