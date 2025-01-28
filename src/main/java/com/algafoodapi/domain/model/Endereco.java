package com.algafoodapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * @Embeddable é uma classe incorporável, ela tem a capacidade
 * de ser incorparada em uma entidade.
 *
 * Indica que ela é parte de alguma outra entidade
 *
 * e todos as propriedades desta classe são refletidas na tabela
 * que incorpora esta classe
 *
 */
@Data
@Embeddable
public class Endereco {

    @Column(name = "endereco_cep")
    private String cep;

    @Column(name = "endereco_logradouro")
    private String logradouro;

    @Column(name = "endereco_numero")
    private String numero;

    @Column(name = "endereco_complemento")
    private String complemento;

    @Column(name = "endereco_bairro")
    private String bairro;

    @ManyToOne
    @JoinColumn(name = "endereco_cidade_id")
    private Cidade cidade;
}
