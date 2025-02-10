package com.algafoodapi.core.modelmapper;

import com.algafoodapi.api.model.CozinhaDTO;
import com.algafoodapi.api.model.RestauranteDTO;
import com.algafoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class)
                .setConverter(context -> {
                    CozinhaDTO cozinhaDTO = new CozinhaDTO();
                    cozinhaDTO.setId(context.getSource().getId());
                    cozinhaDTO.setNome(context.getSource().getNome());

                    Restaurante source = context.getSource();
                    RestauranteDTO dto = new RestauranteDTO();
                    dto.setId(source.getId());
                    dto.setNome(source.getNome());
                    dto.setPrecoFreteBaguiDoido(source.getTaxaFrete());
                    dto.setCozinha(cozinhaDTO);
                    return dto;
                });


        return modelMapper;
    }

}
