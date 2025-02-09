package com.algafoodapi.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteDTO {

    private Long id;
    private String nome;
    private BigDecimal precoFreteBaguiDoido;
    private CozinhaDTO cozinha;

}
