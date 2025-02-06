package com.algafoodapi.api.assembler;

import com.algafoodapi.api.model.CozinhaDTO;
import com.algafoodapi.api.model.RestauranteDTO;
import com.algafoodapi.domain.model.Restaurante;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteDTOAssembler {

    public RestauranteDTO toModel(Restaurante restaurante) {
        CozinhaDTO cozinhaDTO = new CozinhaDTO();
        cozinhaDTO.setId(restaurante.getCozinha().getId());
        cozinhaDTO.setNome(restaurante.getCozinha().getNome());

        RestauranteDTO restauranteDTO = new RestauranteDTO();
        restauranteDTO.setId(restaurante.getId());
        restauranteDTO.setNome(restaurante.getNome());
        restauranteDTO.setTaxaFrete(restaurante.getTaxaFrete());
        restauranteDTO.setCozinha(cozinhaDTO);
        return restauranteDTO;
    }

    public List<RestauranteDTO> toCollectionsModel(Collection<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }

}
