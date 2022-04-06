package br.com.algaworks.algafoodapi.api.controller;

import br.com.algaworks.algafoodapi.api.response.RestauranteDTO;
import br.com.algaworks.algafoodapi.domain.model.Restaurante;
import br.com.algaworks.algafoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private CadastroRestauranteService restauranteService;

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteDTO> consultar(@PathVariable long id) {
        Optional<RestauranteDTO> restaurante = restauranteService.consultar(id);

        return restaurante.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> listar() {
        return ResponseEntity.ok(restauranteService.listar());
    }
}
