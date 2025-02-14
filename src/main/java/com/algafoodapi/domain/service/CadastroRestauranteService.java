package com.algafoodapi.domain.service;

import com.algafoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.algafoodapi.domain.model.Cidade;
import com.algafoodapi.domain.model.Cozinha;
import com.algafoodapi.domain.model.Restaurante;
import com.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroCozinhaService cozinhaService;

    @Autowired
    private CadastroCidadeService cidadeService;

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {

        Long cidadeId = restaurante.getEndereco().getCidade().getId();

        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
        Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);

        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);

        return restauranteRepository.save(restaurante);
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(()-> new RestauranteNaoEncontradoException(restauranteId));
    }

    @Transactional
    public void ativar(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.ativar();
        // Não preciso chamar o método de salvar no banco de dados,
        // dado que anotamos com @Transactional, ele já motifica no
        // banco de forma sincrona.
    }

    @Transactional
    public void inativar(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.inativar();
        // Não preciso chamar o método de salvar no banco de dados,
        // dado que anotamos com @Transactional, ele já motifica no
        // banco de forma sincrona.
    }
}
