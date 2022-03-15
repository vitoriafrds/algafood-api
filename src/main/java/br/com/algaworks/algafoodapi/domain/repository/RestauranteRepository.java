package br.com.algaworks.algafoodapi.domain.repository;

import br.com.algaworks.algafoodapi.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    List<Restaurante> todas();
    Restaurante buscarPorId(Long id);
    void adicionar(Restaurante restaurante);
}
