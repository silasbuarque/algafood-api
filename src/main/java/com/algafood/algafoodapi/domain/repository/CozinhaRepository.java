package com.algafood.algafoodapi.domain.repository;

import com.algafood.algafoodapi.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    List<Cozinha> findTodasByNome(String nome);
    Cozinha findByNome(String nome);
}
