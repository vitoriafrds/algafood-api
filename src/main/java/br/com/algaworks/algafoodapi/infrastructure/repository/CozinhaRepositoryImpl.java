package br.com.algaworks.algafoodapi.infrastructure.repository;

import br.com.algaworks.algafoodapi.api.mapper.CozinhaMapper;
import br.com.algaworks.algafoodapi.domain.model.Cozinha;
import br.com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cozinha> listar() {
        return entityManager.createQuery("from Cozinha", Cozinha.class)
                .getResultList();
    }

    @Override
    public Cozinha buscar(Long id) {
        return entityManager.find(Cozinha.class, id);
    }

    @Override
    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
       return entityManager.merge(cozinha);
    }

    @Override
    @Transactional
    public Cozinha atualizar(long id, Cozinha alteracao) {
        Cozinha cozinha = buscar(id);

        if (cozinha != null) {
            cozinha.setTipo(alteracao.getTipo());
            return salvar(cozinha);
        }

        return null;
    }

    @Override
    @Transactional
    public void remover(Cozinha cozinha) {
        entityManager.remove(cozinha);
    }


}
