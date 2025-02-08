package com.algafoodapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String nome;


    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    /**
     * A estrategia de fetch por padrão dos "ToOne"
     * é o Eager Loading (carregamento ansioso),
     *
     * @EagerLoading = O JPA não quer saber se iremos
     * utilizar ou não a propriedade Cozinha, por ser
     * "ToOne", ele já vai carregar pra gente.
     */
    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable = false) // Caso eu queria trocar o nome da coluna id
    private Cozinha cozinha;

    @Column(nullable = false, columnDefinition = "datetime")
    @CreationTimestamp //Cadastra a hora automaticamente - Hibernate
    private OffsetDateTime dataCadastro;

    @Column(nullable = false, columnDefinition = "datetime")
    @UpdateTimestamp //Atualiza a hora automaticamente - Hibernate
    private OffsetDateTime dataAtualizacao;

    /**
     * @Embedded indica que a classe "Endereço"
     * faz parte da classe Restaurante
     */
    @Embedded
    private Endereco endereco;

    /**
     *  name = tabela que irá pegar FK de restaurante e forma de pagamento para
     *  fazer o relacionamento entre as duas entidades;
     *
     *  joinColmns = id da FK da nova tabela que irá fazer o relacionamento entre
     *  restaurante e forma de pagamento
     *
     *  inverseJoinColumns = FK da tabela "inversa", que seria forma de pagamento
     */
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
                joinColumns = @JoinColumn(name = "restaurante_id"),
                inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    /**
     * Já a estratégia do "ToMany" padrão é o Lazy Loading.
     *
     * @LazyLoading = Carrega os dados apenas quando
     * necessita deles.
     */
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

}
