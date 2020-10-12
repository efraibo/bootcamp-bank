package com.zup.bootcamp.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class ObjetoPersistente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
