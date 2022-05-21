package br.com.algaworks.algafoodapi.domain.repository;

import br.com.algaworks.algafoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
 import java.util.Optional;

@Repository
public interface RestauranteRepository
        extends JpaRepository<Restaurante, Long>, RestauranteRepositoryCustom {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
    Optional<Restaurante> findFirstByNomeContaining(String nome);
    List<Restaurante> findTop2ByNomeContaining(String nome);
}
