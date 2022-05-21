package br.com.algaworks.algafoodapi.infrastructure;

import br.com.algaworks.algafoodapi.api.response.RestauranteDTO;
import br.com.algaworks.algafoodapi.domain.model.Restaurante;
import br.com.algaworks.algafoodapi.domain.repository.RestauranteCustomRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestauranteCustomRepositoryImpl implements RestauranteCustomRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> consultarPorNomeETaxaFrete(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {

        var parametros = new HashMap<String, Object>();
        var query = new StringBuilder();
        query.append("from Restaurante where 0 = 0 ");

        if (StringUtils.hasText(nome)) {
            query.append("and nome like :nome ");
            parametros.put("nome", "%" + nome + "%");
        }

        if (taxaInicial != null) {
            query.append("and taxaFrete >= :taxaInicial ");
            parametros.put("taxaInicial", taxaInicial);
        }

        if (taxaFinal != null) {
            query.append("and taxaFrete <= :taxaFinal ");
            parametros.put("taxaInicial", taxaInicial);
        }

        TypedQuery<Restaurante> result =  manager.createQuery(query.toString(), Restaurante.class);
        parametros.forEach((parametro, condicao) -> result.setParameter(parametro, condicao));

        return result.getResultList();
    }

}
