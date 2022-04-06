package br.com.algaworks.algafoodapi.domain.service;

import br.com.algaworks.algafoodapi.domain.mapper.CozinhaMapper;
import br.com.algaworks.algafoodapi.api.response.CozinhaDTO;
import br.com.algaworks.algafoodapi.domain.exceptions.EntidadeEmUsoException;
import br.com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.algaworks.algafoodapi.domain.model.Cozinha;
import br.com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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

    public Optional<CozinhaDTO> consultar(long id) {
        return repository.findById(id).map(c -> {
            CozinhaDTO cozinha = new CozinhaDTO();
            cozinha.setId(c.getId());
            cozinha.setTipo(c.getTipo());
            return cozinha;
        });
    }

    public Optional<CozinhaDTO> alterar(long id, CozinhaDTO cozinha) {
        Optional<Cozinha> cozinhaAtual = repository.findById(id);

        if (cozinhaAtual.isPresent()) {
            cozinhaAtual.get().setTipo(cozinha.getTipo());
            return Optional.of(mapper.toDTO(repository.save(cozinhaAtual.get())));
        }

        return Optional.empty();
    }

    public List<CozinhaDTO> listar() {
        return repository.findAll()
                .stream().map(c -> mapper.toDTO(c))
                .collect(Collectors.toList());
    }

    public void excluir(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException error) {
            throw new EntidadeNaoEncontradaException(String.format("Cozinha de id %d não foi encontrada", id));
        } catch (DataIntegrityViolationException error) {
            throw new EntidadeEmUsoException(String.format("O id %d esta em uso em outra entidade", id));
        }

    }
}
