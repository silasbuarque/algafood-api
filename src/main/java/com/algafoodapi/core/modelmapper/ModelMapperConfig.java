package com.algafoodapi.core.modelmapper;

import com.algafoodapi.api.model.EnderecoDTO;
import com.algafoodapi.api.model.FormaPagamentoDTO;
import com.algafoodapi.api.model.RestauranteDTO;
import com.algafoodapi.domain.model.Endereco;
import com.algafoodapi.domain.model.FormaPagamento;
import com.algafoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

//        modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class)
//                .addMapping(Restaurante::getTaxaFrete, RestauranteDTO::getTaxaFrete);

        var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class);

        enderecoToEnderecoModelTypeMap.<String>addMapping(
                enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                (enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));

        return modelMapper;
    }

}
