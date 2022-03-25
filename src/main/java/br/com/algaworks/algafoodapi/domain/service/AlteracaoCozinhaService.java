package br.com.algaworks.algafoodapi.domain.service;

import br.com.algaworks.algafoodapi.api.mapper.CozinhaMapper;
import br.com.algaworks.algafoodapi.api.response.CozinhaDTO;
import br.com.algaworks.algafoodapi.domain.model.Cozinha;
import br.com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlteracaoCozinhaService {
    private CozinhaRepository repository;
    private CozinhaMapper mapper;

    public AlteracaoCozinhaService(CozinhaRepository repository) {
        this.repository = repository;
        this.mapper = CozinhaMapper.getInstance();
    }

    public Optional<CozinhaDTO> alterar(long id, CozinhaDTO cozinha) {
        Optional<Cozinha> cozinhaAtual = repository.findById(id);

        if (cozinhaAtual.isPresent()) {
            cozinhaAtual.get().setTipo(cozinha.getTipo());
            return Optional.of(mapper.toDTO(repository.save(cozinhaAtual.get())));
        }

        return Optional.empty();
    }
}
