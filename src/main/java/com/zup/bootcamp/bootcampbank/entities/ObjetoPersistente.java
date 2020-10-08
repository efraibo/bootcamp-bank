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
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@EqualsAndHashCode(of = {"id"})
@MappedSuperclass
public class ObjetoPersistente implements Persistable<Long> {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    //Retorna se o Persistable novo ou já foi persistido.
    @Override
    public boolean isNew() {
        return this.id == null;
    }
}
