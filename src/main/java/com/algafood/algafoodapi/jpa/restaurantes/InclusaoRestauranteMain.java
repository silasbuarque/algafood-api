package com.algafood.algafoodapi.jpa.restaurantes;

import com.algafood.algafoodapi.AlgafoodApiApplication;
import com.algafood.algafoodapi.domain.model.Cozinha;
import com.algafood.algafoodapi.domain.model.Restaurante;
import com.algafood.algafoodapi.domain.repository.CozinhaRepository;
import com.algafood.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class InclusaoRestauranteMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

        Restaurante restaurante1 = new Restaurante();
        restaurante1.setNome("Tempero da Sandra");
        restaurante1.setTaxaFrete(new BigDecimal("8.00"));

        Restaurante restaurante2 = new Restaurante();
        restaurante2.setNome("Coco no teu bambu");
        restaurante2.setTaxaFrete(new BigDecimal("25.00"));

        restaurante1 = restauranteRepository.salvar(restaurante1);
        restaurante2 = restauranteRepository.salvar(restaurante2);

        System.out.printf("%s - %s%n", restaurante1.getId(), restaurante1.getNome());
        System.out.printf("%s - %s%n", restaurante2.getId(), restaurante2.getNome());

    }

}
