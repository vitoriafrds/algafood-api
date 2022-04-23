package br.com.algaworks.algafoodapi.domain.service;

import br.com.algaworks.algafoodapi.api.response.RestauranteDTO;
import br.com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.algaworks.algafoodapi.domain.mapper.RestauranteMapper;
import br.com.algaworks.algafoodapi.domain.model.Cozinha;
import br.com.algaworks.algafoodapi.domain.model.Restaurante;
import br.com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import br.com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroRestauranteService {
    private RestauranteMapper mapper = RestauranteMapper.get();

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<RestauranteDTO> listar() {
        return mapper.toRepresentationModel(restauranteRepository.findAll());
    }

    public Optional<RestauranteDTO> consultar(Long id) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);
        return restaurante.map(value -> mapper.toRepresentationModel(value));
    }

    public RestauranteDTO cadastrar(RestauranteDTO restaurante) {
        Optional<Cozinha> cozinha = consultarCozinha(restaurante.getCozinha().getId());

        if (cozinha.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Cozinha nao cadastrada");
        }

        return mapper.toRepresentationModel(restauranteRepository.save(mapper.toModel(restaurante)));
    }

    public RestauranteDTO atualizar(long id, RestauranteDTO alteracoes) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);

        if (restaurante.isPresent()) {
            if (alteracoes.getNome() != null) {
                restaurante.get().setNome(alteracoes.getNome());
            }

            if (alteracoes.getTaxaFrete() != null) {
                restaurante.get().setTaxaFrete(alteracoes.getTaxaFrete());
            }

            if (alteracoes.getCozinha() != null && alteracoes.getCozinha().getId() != null) {
                Optional<Cozinha> cozinha = consultarCozinha(alteracoes.getCozinha().getId());

                if (cozinha.isPresent()) {
                    restaurante.get().getCozinha().setId(alteracoes.getCozinha().getId());
                } else {
                    throw new EntidadeNaoEncontradaException("A cozinha informada nao existe na nossa base de dados");
                }
            }
            
            restauranteRepository.save(restaurante.get());

            return mapper.toRepresentationModel(restaurante.get());
        }

        throw new EntidadeNaoEncontradaException("O restaurante informado para atualizacao nao existe.");
    }

    private Optional<Cozinha> consultarCozinha(long id) {
        return cozinhaRepository.findById(id);
    }
}
