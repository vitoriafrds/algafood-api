package br.com.algaworks.algafoodapi.infrastructure;

import br.com.algaworks.algafoodapi.api.response.RestauranteDTO;
import br.com.algaworks.algafoodapi.domain.model.Restaurante;
import br.com.algaworks.algafoodapi.domain.repository.RestauranteCustomRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteCustomRepositoryImpl implements RestauranteCustomRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> consultarPorNomeETaxaFrete(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {

        var query = "from Restaurante where nome like :nome and "
                + "taxaFrete between :taxaInicial and :taxaFinal";

        return manager.createQuery(query, Restaurante.class)
                .setParameter("nome", "%" + nome + "%")
                .setParameter("taxaInicial", taxaInicial)
                .setParameter("taxaFinal", taxaFinal)
                .getResultList();
    }
}
