package com.algafood.algafoodapi.domain.repository;

import com.algafood.algafoodapi.domain.model.Cozinha;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CozinhaRepository {

    List<Cozinha> listar();
    List<Cozinha> consultarPorNome(String nome);
    Cozinha buscar(Long id);
    Cozinha salvar(Cozinha cozinha);
    void remover(Long id);

}
