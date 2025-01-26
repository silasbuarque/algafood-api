package com.algafood.algafoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    DADOS_INVALIDOS("/dados-invalidos","Dados invalidos"),
    ERRO_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parametro invalido"),
    REQUISICAO_INVALIDA("/requisicao-invalida", "Requisição inválida"),
    MENSAGEM_INCOMPREENSIVEL("/menssagem-incompreensivel", "Mensagem incompreensivel"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso nao encontrado"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/regra-de-negocio-violada", "Regra de negocio foi violada");

    private String path;
    private String title;

    ProblemType(String path, String title) {
        this.path = "https://algafood.com" + path;
        this.title = title;
    }
}
