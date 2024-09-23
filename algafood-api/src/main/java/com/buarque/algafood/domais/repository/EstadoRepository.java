package com.buarque.algafood.domais.repository;

import com.buarque.algafood.domais.model.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> listar();
    Estado buscar(Long id);
    Estado salvar(Estado estado);
    void remover(Estado estado);

}
