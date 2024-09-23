package com.buarque.algafood.domais.api.controller;


import com.buarque.algafood.domais.model.Cozinha;
import com.buarque.algafood.domais.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> cozinhas() {
        return cozinhaRepository.listar();
    }

}
