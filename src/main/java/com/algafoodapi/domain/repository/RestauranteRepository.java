package com.algafoodapi.domain.repository;

import com.algafoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

    /**
     * Fazemos uma consulta personalizada para que não seja necessário,
     * na hora de buscar os dados, fazer tantas consultas.
     *
     * Para os "ToOne" (que é o caso da cozinha), apenas "join fetch"
     * funciona.
     *
     * Já para "ToMany" (o formasPagamento), é necessário utilizar o "left
     * join fetch". Pq caso o Restaurante não tenha nenhuma forma de pagamento,
     * ele será retornado do mesmo jeito. Se utilizarmos apenas o Join Fetch,
     * o restaurante que não tiver formasPagamento, não será retornado.
     *
     */
    @Query("from Restaurante r join fetch r.cozinha left join fetch r.formasPagamento")
    List<Restaurante> findAll();

    List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

//    @Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
    List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);

//	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);

    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

    List<Restaurante> findTop2ByNomeContaining(String nome);

    int countByCozinhaId(Long cozinha);

    List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);

}
