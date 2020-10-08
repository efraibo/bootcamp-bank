package com.zup.bootcamp.bootcampbank.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@EqualsAndHashCode(of = {"id"})
@MappedSuperclass
public class ObjetoPersistente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
