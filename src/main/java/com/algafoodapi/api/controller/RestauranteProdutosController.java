package com.algafoodapi.api.controller;

import com.algafoodapi.api.assembler.ProdutoInputDisassembler;
import com.algafoodapi.api.assembler.ProdutosDTOAssembler;
import com.algafoodapi.api.model.ProdutosDTO;
import com.algafoodapi.api.model.input.ProdutoInput;
import com.algafoodapi.domain.model.Produto;
import com.algafoodapi.domain.model.Restaurante;
import com.algafoodapi.domain.service.CadastroProdutosService;
import com.algafoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutosController {

    @Autowired
    private CadastroProdutosService produtosService;

    @Autowired
    private CadastroRestauranteService restauranteService;

    @Autowired
    private ProdutosDTOAssembler produtosDTOAssembler;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @GetMapping
    public List<ProdutosDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        return produtosDTOAssembler.toListDTO(restaurante.getProdutos());
    }

    //Buscar por id
    @GetMapping("/{produtoId}")
    public ProdutosDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = produtosService.buscarOuFalhar(restauranteId, produtoId);
        return produtosDTOAssembler.toDTO(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutosDTO associar(@PathVariable Long restauranteId, @RequestBody ProdutoInput produtoInput) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);

        produto = produtosService.adicionar(produto);

        return produtosDTOAssembler.toDTO(produto);
    }

    @PutMapping("/{produtoId}")
    public ProdutosDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId, @RequestBody ProdutoInput produtoInput) {
        Produto produtoAtual = produtosService.buscarOuFalhar(restauranteId, produtoId);

        produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);

        produtoAtual = produtosService.adicionar(produtoAtual);

        return produtosDTOAssembler.toDTO(produtoAtual);
    }

}
