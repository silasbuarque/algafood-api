package com.algafoodapi.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteInput {

    @NotBlank
    private String nome;

//    @DecimalMin("0") -> Só aceita valor igual ou maior que o especificado
//    @Multiplo(numero = 5)
    @NotNull
    @PositiveOrZero // -> Aceita numeros positivos ou zero;
    private BigDecimal taxaFrete;

    @Valid
    @NotNull
    private CozinhaIdInput cozinha;

}
