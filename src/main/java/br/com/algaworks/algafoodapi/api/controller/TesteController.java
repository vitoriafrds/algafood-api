package br.com.algaworks.algafoodapi.api.controller;

import br.com.algaworks.algafoodapi.api.response.RestauranteDTO;
import br.com.algaworks.algafoodapi.domain.mapper.RestauranteMapper;
import br.com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import br.com.algaworks.algafoodapi.infrastructure.spec.RestauranteFreteGratisSpec;
import br.com.algaworks.algafoodapi.infrastructure.spec.RestauranteNomeSemelhanteSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/testes")
public class TesteController {

    @Autowired
    private RestauranteRepository repository;
    private RestauranteMapper mapper = RestauranteMapper.get();

    public List<RestauranteDTO> findWithCriteriaQuery(@RequestParam String nome,
                                                      @RequestParam BigDecimal taxaInicial,
                                                      @RequestParam BigDecimal taxaFinal  ) {
        return mapper.toRepresentationModel(
                repository.find(nome, taxaInicial, taxaFinal));
    }

    public List<RestauranteDTO> consultarPorNomeEFreteGratos(@RequestParam String nome,
                                                      @RequestParam BigDecimal taxaInicial,
                                                      @RequestParam BigDecimal taxaFinal  ) {
        var comNomeSemelhante = new RestauranteNomeSemelhanteSpec(nome);
        var comFreteGratis = new RestauranteFreteGratisSpec();

        return mapper.toRepresentationModel(
                repository.findAll(comNomeSemelhante.and(comFreteGratis)));
    }
}
