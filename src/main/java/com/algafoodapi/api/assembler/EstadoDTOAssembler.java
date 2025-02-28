package com.algafoodapi.api.assembler;

import com.algafoodapi.api.model.EstadoDTO;
import com.algafoodapi.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstadoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public EstadoDTO toDTO(Estado estado) {
        return modelMapper.map(estado, EstadoDTO.class);
    }

    public List<EstadoDTO> toCollectionsModel(List<Estado> estados) {
        return estados.stream()
                .map(estado -> toDTO(estado))
                .collect(Collectors.toList());
    }

}
