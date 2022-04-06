package br.com.algaworks.algafoodapi.domain.service;

import br.com.algaworks.algafoodapi.api.response.RestauranteDTO;
import br.com.algaworks.algafoodapi.domain.mapper.RestauranteMapper;
import br.com.algaworks.algafoodapi.domain.model.Restaurante;
import br.com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;
    private RestauranteMapper mapper = RestauranteMapper.get();

    public List<RestauranteDTO> listar() {
        return mapper.toRepresentationModel(restauranteRepository.findAll());
    }

    public Optional<RestauranteDTO> consultar(Long id) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);
        return restaurante.map(value -> mapper.toRepresentationModel(value));
    }

    public RestauranteDTO cadastrar(RestauranteDTO restaurante) {
        Restaurante novoRestaurante = restauranteRepository.save(mapper.toModel(restaurante));

        return mapper.toRepresentationModel(novoRestaurante);
    }
}
