package com.algafoodapi.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioAlteraSenhaInput {

    @NotBlank
    private String senhaAtual;

    @NotBlank
    private String novaSenha;

}
