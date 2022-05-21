package br.com.algaworks.algafoodapi.domain.repository;

import br.com.algaworks.algafoodapi.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryCustom {
    List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);
}
