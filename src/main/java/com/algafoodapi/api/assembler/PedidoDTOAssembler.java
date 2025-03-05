package com.algafoodapi.api.assembler;

import com.algafoodapi.api.model.PedidoDTO;
import com.algafoodapi.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoDTO toDTO(Pedido pedido) {
        return modelMapper.map(pedido, PedidoDTO.class);
    }

    public List<PedidoDTO> toListDTO(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
