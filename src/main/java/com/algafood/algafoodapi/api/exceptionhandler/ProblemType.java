package com.algafood.algafoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade nao encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/regra-de-negocio-violada", "Regra de negocio foi violada");

    private String path;
    private String title;

    ProblemType(String path, String title) {
        this.path = "https://algafood.com" + path;
        this.title = title;
    }
}
