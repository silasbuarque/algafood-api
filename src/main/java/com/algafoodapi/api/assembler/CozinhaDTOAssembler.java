package com.algafoodapi.api.assembler;

import com.algafoodapi.api.model.CozinhaDTO;
import com.algafoodapi.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CozinhaDTO toDTO(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaDTO.class);
    }

    public List<CozinhaDTO> toCollectionsDTO(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(cozinha -> toDTO(cozinha))
                .collect(Collectors.toList());
    }

}
