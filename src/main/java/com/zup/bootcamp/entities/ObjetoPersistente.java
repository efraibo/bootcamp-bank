package com.zup.bootcamp.entities;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class ObjetoPersistente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
