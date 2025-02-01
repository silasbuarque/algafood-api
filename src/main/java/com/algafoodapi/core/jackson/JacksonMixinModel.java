package com.algafoodapi.core.jackson;

import com.algafoodapi.api.model.mixin.CidadeMixin;
import com.algafoodapi.api.model.mixin.CozinhaMixin;
import com.algafoodapi.api.model.mixin.RestauranteMixin;
import com.algafoodapi.domain.model.Cidade;
import com.algafoodapi.domain.model.Cozinha;
import com.algafoodapi.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModel extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JacksonMixinModel() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }

}
