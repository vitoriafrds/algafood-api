package br.com.algaworks.algafoodapi.api.controller;

import br.com.algaworks.algafoodapi.api.response.CozinhaDTO;
import br.com.algaworks.algafoodapi.api.response.EstadoDTO;
import br.com.algaworks.algafoodapi.domain.exceptions.EntidadeEmUsoException;
import br.com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.algaworks.algafoodapi.domain.service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/estados", produces = APPLICATION_JSON_VALUE)
public class EstadoController {

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EstadoDTO>> listar() {
        return ResponseEntity.ok(cadastroEstadoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoDTO> consultar(@PathVariable long id) {
        Optional<EstadoDTO> cozinha = cadastroEstadoService.consultar(id);

        return cozinha.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Optional<EstadoDTO> salvar(@RequestBody EstadoDTO estado) {
        return cadastroEstadoService.cadastrar(estado);
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Void> excluir(@PathVariable long cozinhaId) {
        try {
            cadastroEstadoService.excluir(cozinhaId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeEmUsoException err) {
            return ResponseEntity.status(CONFLICT).build();

        } catch (EntidadeNaoEncontradaException err) {
            return ResponseEntity.notFound().build();
        }
    }
}
