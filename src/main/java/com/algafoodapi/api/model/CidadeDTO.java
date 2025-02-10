package com.algafoodapi.api.model;

import com.algafoodapi.domain.model.Estado;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeDTO {

    private Long id;
    private String nome;
    private EstadoDTO estado;

}
