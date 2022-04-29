package br.com.algaworks.algafoodapi.infrastructure;

import br.com.algaworks.algafoodapi.domain.model.Cozinha;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CozinhaRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Cozinha> consultarPorNome(String nome) {
        return entityManager.createQuery("from Cozinha where nome = :nome", Cozinha.class)
                .setParameter("nome", nome)
                .getResultList();
    }
}
