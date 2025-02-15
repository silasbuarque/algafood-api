package com.algafoodapi.api.assembler;

import com.algafoodapi.api.model.input.UsuarioAdicionarInput;
import com.algafoodapi.api.model.input.UsuarioAtualizarInput;
import com.algafoodapi.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainObject(UsuarioAdicionarInput usuarioAdicionarInput) {
        return modelMapper.map(usuarioAdicionarInput, Usuario.class);
    }

    public void copyToDomainObject(UsuarioAdicionarInput usuarioAdicionarInput, Usuario usuario) {
        modelMapper.map(usuarioAdicionarInput, usuario);
    }

    public void copyToDomainObject(UsuarioAtualizarInput usuarioAtualizarInput, Usuario usuario) {
        modelMapper.map(usuarioAtualizarInput, usuario);
    }
}
