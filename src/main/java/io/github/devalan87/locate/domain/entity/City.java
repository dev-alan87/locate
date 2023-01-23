package io.github.devalan87.locate.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "tb_city")
@Data @NoArgsConstructor @AllArgsConstructor
public class City {

    @Id
    private Long id;
    @Column(length = 50)
    private String name;
    private Long population;

}
