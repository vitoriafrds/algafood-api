package br.com.algaworks.algafoodapi.domain.repository;

import br.com.algaworks.algafoodapi.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

}
