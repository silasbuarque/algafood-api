package com.algafoodapi.domain.model;

import com.algafoodapi.core.validation.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

//    @NotNull -> Não aceita null, mas aceita string vazia
//    @NotEmpty -> Não aceita null e nem String vazia, mas aceita String com espaço
    @NotBlank // -> Não aceita null, String vazia e nem String com espaço
    @Column(nullable = false)
    private String nome;

//    @DecimalMin("0") -> Só aceita valor igual ou maior que o especificado
    @PositiveOrZero // -> Aceita numeros positivos ou zero;
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
//    @JsonIgnore
    @Valid
    @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable = false) // Caso eu queria trocar o nome da coluna id
    private Cozinha cozinha;

    @JsonIgnore
    @Column(nullable = false, columnDefinition = "datetime")
    @CreationTimestamp //Cadastra a hora automaticamente - Hibernate
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @Column(nullable = false, columnDefinition = "datetime")
    @UpdateTimestamp //Atualiza a hora automaticamente - Hibernate
    private LocalDateTime dataAtualizacao;

    /**
     * @Embedded indica que a classe "Endereço"
     * faz parte da classe Restaurante
     */
    @JsonIgnore
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
    @JsonIgnore
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
    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

}
