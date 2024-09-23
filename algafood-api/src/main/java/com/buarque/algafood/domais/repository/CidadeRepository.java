package com.buarque.algafood.domais.repository;

import com.buarque.algafood.domais.model.Cidade;

import java.util.List;

public interface CidadeRepository {

    List<Cidade> listar();
    Cidade buscar(Long id);
    Cidade salvar(Cidade cidade);
    void remover(Cidade cidade);

}
