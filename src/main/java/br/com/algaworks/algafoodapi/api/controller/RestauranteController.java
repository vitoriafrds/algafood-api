package br.com.algaworks.algafoodapi.api.controller;

import br.com.algaworks.algafoodapi.api.response.RestauranteDTO;
import br.com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.algaworks.algafoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<RestauranteDTO> cadastrar(@RequestBody RestauranteDTO restaurante) {
        try {
            RestauranteDTO resultado = restauranteService.cadastrar(restaurante);

            return ResponseEntity.ok(resultado);
        } catch (EntidadeNaoEncontradaException erro) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable long id, RestauranteDTO alteracao) {
        try {
            restauranteService.atualizar(id, alteracao);
            return ResponseEntity.ok().build();
        } catch (EntidadeNaoEncontradaException erro) {
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }
}
