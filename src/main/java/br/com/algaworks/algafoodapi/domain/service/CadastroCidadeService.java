package br.com.algaworks.algafoodapi.domain.service;

import br.com.algaworks.algafoodapi.api.response.CidadeDTO;
import br.com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.algaworks.algafoodapi.domain.mapper.CidadeMapper;
import br.com.algaworks.algafoodapi.domain.model.Cidade;
import br.com.algaworks.algafoodapi.domain.model.Estado;
import br.com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import br.com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CadastroCidadeService {
    private CidadeMapper mapper = CidadeMapper.get();

    @Autowired
    private CidadeRepository  cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Optional<CidadeDTO> cadastrar(CidadeDTO cidade) {
        Optional<Estado> estado = estadoRepository.findById(cidade.getEstado().getId());

        if (estado.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Estado informado não existe");
        }

        Cidade novaCidade = cidadeRepository.save(mapper.map(cidade));

        return  Optional.of(mapper.map(novaCidade));
    }

    public Optional<CidadeDTO> consultar(long id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);

        if (cidade.isPresent()) {
            return Optional.of(mapper.map(cidade.get()));
        }

        return Optional.empty();
    }

    public List<CidadeDTO> listar() {
        return cidadeRepository.findAll().stream()
                .map(cidade -> mapper.map(cidade)).collect(Collectors.toList());
    }
}
