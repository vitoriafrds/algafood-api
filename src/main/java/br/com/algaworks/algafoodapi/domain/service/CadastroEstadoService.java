package br.com.algaworks.algafoodapi.domain.service;

import br.com.algaworks.algafoodapi.api.response.EstadoDTO;
import br.com.algaworks.algafoodapi.domain.exceptions.EntidadeEmUsoException;
import br.com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.algaworks.algafoodapi.domain.mapper.EstadoMapper;
import br.com.algaworks.algafoodapi.domain.model.Estado;
import br.com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    private EstadoMapper estadoMapper = EstadoMapper.get();

    public Optional<EstadoDTO> cadastrar(EstadoDTO estado) {
        Estado novoEstado = estadoRepository.save(estadoMapper.map(estado));
        return Optional.of(estadoMapper.map(novoEstado));
    }

    public void excluir(long id) {
        Optional<Estado> estado = estadoRepository.findById(id);

        if (estado.isPresent()) {
            try {
                estadoRepository.deleteById(id);
            } catch (DataIntegrityViolationException violation) {
                throw new EntidadeEmUsoException(String.format("O estado {%d} nao pode ser excluido pois esta em uso", id));
            }
        } else {
            throw new EntidadeNaoEncontradaException("O estado informado nao existe");
        }
    }

    public Optional<EstadoDTO> consultar(long id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        return estado.map(value -> estadoMapper.map(value));
    }

    public List<EstadoDTO> listar() {
        List<Estado> estados = estadoRepository.findAll();
        return estadoMapper.map(estados);
    }
}
