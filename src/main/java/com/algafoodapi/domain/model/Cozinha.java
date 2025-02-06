package com.algafoodapi.domain.model;


import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@JsonRootName("cozinha")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    /**
     * mappedBy = Nome do atributo Cozinha que está mapeado na classe Restaurante
     */
    @OneToMany(mappedBy = "cozinha")
    private List<Restaurante> restaurantes;

}

