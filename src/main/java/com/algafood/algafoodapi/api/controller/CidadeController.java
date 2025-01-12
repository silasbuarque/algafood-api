package com.algafood.algafoodapi.api.controller;

import com.algafood.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapi.domain.model.Cidade;
import com.algafood.algafoodapi.domain.model.Estado;
import com.algafood.algafoodapi.domain.repository.CidadeRepository;
import com.algafood.algafoodapi.domain.repository.EstadoRepository;
import com.algafood.algafoodapi.domain.service.CadastroCidadeService;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cidadeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
         Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);

         if (cidade.isPresent()) {
             return ResponseEntity.ok(cidade.get());
         }

         return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Cidade cidade) {

        try {
            cidade = cidadeService.salvar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<?> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {

        try {
            Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidadeId);

            if (cidadeAtual.isPresent()) {
                BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");

                Cidade cidadeSalva = cidadeService.salvar(cidadeAtual.get());
                return ResponseEntity.ok(cidadeSalva);
            }

            return ResponseEntity.notFound().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<?> remover(@PathVariable Long cidadeId) {
        try {
            cidadeService.remover(cidadeId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
