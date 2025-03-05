package com.algafoodapi.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {

    CRIADO("Criado"),
    CONFIRMADO("Confirmado", CRIADO),
    ENTREGUE("Entregue", CONFIRMADO),
    CANCELADO("Cancelado", CRIADO);

    private String descricao;
    private List<StatusPedido> statusAnteiores;

    StatusPedido(String descricao, StatusPedido... statusAnteiores) {
        this.descricao = descricao;
        this.statusAnteiores = Arrays.asList(statusAnteiores);
    }

    public String getDescricao() {
        return this.descricao;
    }

    public boolean naoPodeAlterarPara(StatusPedido status) {
        return !status.statusAnteiores.contains(this);
    }

}
