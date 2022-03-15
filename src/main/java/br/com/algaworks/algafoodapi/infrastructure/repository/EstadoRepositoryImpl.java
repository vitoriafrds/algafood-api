package br.com.algaworks.algafoodapi.infrastructure.repository;

import br.com.algaworks.algafoodapi.domain.model.Estado;
import br.com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> listar() {
        return manager.createQuery("from Estado", Estado.class)
                .getResultList();
    }
}
