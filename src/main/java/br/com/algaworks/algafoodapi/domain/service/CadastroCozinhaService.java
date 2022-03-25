package br.com.algaworks.algafoodapi.domain.service;

import br.com.algaworks.algafoodapi.api.mapper.CozinhaMapper;
import br.com.algaworks.algafoodapi.api.response.CozinhaDTO;
import br.com.algaworks.algafoodapi.domain.model.Cozinha;
import br.com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroCozinhaService {
    private CozinhaMapper mapper;
    private CozinhaRepository repository;

    @Autowired
    public CadastroCozinhaService(CozinhaRepository repository) {
        this.repository = repository;
        this.mapper = CozinhaMapper.getInstance();
    }

    public Optional<CozinhaDTO> cadastrar(CozinhaDTO cozinha) {
        return Optional.of(mapper.toDTO(repository.save(mapper.toModel(cozinha))));
    }
}
