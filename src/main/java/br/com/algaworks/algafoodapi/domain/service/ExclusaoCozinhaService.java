package br.com.algaworks.algafoodapi.domain.service;

import br.com.algaworks.algafoodapi.domain.model.Cozinha;
import br.com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExclusaoCozinhaService {
    private CozinhaRepository cozinhaRepository;

    @Autowired
    public ExclusaoCozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    public void excluir(Cozinha cozinha) {
      cozinhaRepository.delete(cozinha);
    }
}
