package com.algafood.algafoodapi.api.controller;

import com.algafood.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapi.domain.model.Estado;
import com.algafood.algafoodapi.domain.repository.EstadoRepository;
import com.algafood.algafoodapi.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService estadoService;

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
        Optional<Estado> estado = estadoRepository.findById(estadoId);

        if (estado.isPresent()) {
            return ResponseEntity.ok(estado.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Estado> salvar(@RequestBody Estado estado) {
        estado = estadoService.salvar(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(estado);
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
        Optional<Estado> estaduAtual = estadoRepository.findById(estadoId);

        if (estaduAtual.isPresent()) {
            BeanUtils.copyProperties(estado, estaduAtual.get(), "id");
            Estado estadoSalvo = estadoService.salvar(estaduAtual.get());
            return ResponseEntity.ok(estadoSalvo);
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> remover(@PathVariable Long estadoId) {
        try {
            estadoService.remover(estadoId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
