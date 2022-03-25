package br.com.algaworks.algafoodapi.domain.service;

import br.com.algaworks.algafoodapi.api.mapper.CozinhaMapper;
import br.com.algaworks.algafoodapi.api.response.CozinhaDTO;
import br.com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListagemCozinhasService {
    private CozinhaRepository repository;
    private CozinhaMapper mapper;

    public ListagemCozinhasService(CozinhaRepository repository) {
        this.repository = repository;
        this.mapper = CozinhaMapper.getInstance();
    }

    public List<CozinhaDTO> listar() {
        return repository.findAll()
                .stream().map(c -> mapper.toDTO(c))
                .collect(Collectors.toList());
    }
}
