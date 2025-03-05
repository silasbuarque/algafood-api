package com.algafoodapi.api.assembler;

import com.algafoodapi.api.model.ProdutosDTO;
import com.algafoodapi.api.model.UsuarioDTO;
import com.algafoodapi.domain.model.Produto;
import com.algafoodapi.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutosDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ProdutosDTO toDTO(Produto produto) {
        return modelMapper.map(produto, ProdutosDTO.class);
    }

    public List<ProdutosDTO> toListDTO(List<Produto> produtos) {
        return produtos.stream()
                .map(produto -> toDTO(produto))
                .collect(Collectors.toList());
    }

}
