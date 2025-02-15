package com.algafoodapi.domain.repository;

import com.algafoodapi.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}
