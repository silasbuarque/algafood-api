package com.algafoodapi.api.assembler;

import com.algafoodapi.api.model.CidadeDTO;
import com.algafoodapi.api.model.RestauranteDTO;
import com.algafoodapi.domain.model.Cidade;
import com.algafoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CidadeDTO toModel(Cidade cidade) {
        return modelMapper.map(cidade, CidadeDTO.class);
    }

    public List<CidadeDTO> toCollectionsModel(List<Cidade> cidades) {
        return cidades.stream()
                .map(cidade -> toModel(cidade))
                .collect(Collectors.toList());
    }

}
