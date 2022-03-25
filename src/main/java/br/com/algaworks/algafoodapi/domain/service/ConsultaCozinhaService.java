package br.com.algaworks.algafoodapi.domain.service;

import br.com.algaworks.algafoodapi.api.mapper.CozinhaMapper;
import br.com.algaworks.algafoodapi.api.response.CozinhaDTO;
import br.com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultaCozinhaService {
    private CozinhaRepository repository;
    private CozinhaMapper mapper;

    public ConsultaCozinhaService(CozinhaRepository repository) {
        this.repository = repository;
        this.mapper = CozinhaMapper.getInstance();
    }

    public Optional<CozinhaDTO> consultar(long id) {
        return repository.findById(id).map(c -> {
            CozinhaDTO cozinha = new CozinhaDTO();
            cozinha.setId(c.getId());
            cozinha.setTipo(c.getTipo());
            return cozinha;
        });
    }
}
