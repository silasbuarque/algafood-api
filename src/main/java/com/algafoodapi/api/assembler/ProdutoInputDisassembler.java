package com.algafoodapi.api.assembler;

import com.algafoodapi.api.model.ProdutosDTO;
import com.algafoodapi.api.model.input.ProdutoInput;
import com.algafoodapi.api.model.input.UsuarioAdicionarInput;
import com.algafoodapi.api.model.input.UsuarioAtualizarInput;
import com.algafoodapi.domain.model.Produto;
import com.algafoodapi.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Produto toDomainObject(ProdutoInput produtoInput) {
        return modelMapper.map(produtoInput, Produto.class);
    }

    public void copyToDomainObject(ProdutoInput produtoInput, Produto produto) {
        modelMapper.map(produtoInput, produto);
    }

}
