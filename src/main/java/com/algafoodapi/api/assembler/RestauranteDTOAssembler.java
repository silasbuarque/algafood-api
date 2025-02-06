package com.algafoodapi.api.assembler;

import com.algafoodapi.api.model.CozinhaDTO;
import com.algafoodapi.api.model.RestauranteDTO;
import com.algafoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestauranteDTO toModel(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteDTO.class);
    }

    public List<RestauranteDTO> toCollectionsModel(Collection<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }

}
