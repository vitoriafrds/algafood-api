package br.com.algaworks.algafoodapi.api.controller;

import br.com.algaworks.algafoodapi.api.mapper.CozinhaMapper;
import br.com.algaworks.algafoodapi.api.response.CozinhaDTO;
import br.com.algaworks.algafoodapi.domain.exceptions.EntidadeEmUsoException;
import br.com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.algaworks.algafoodapi.domain.service.CadastroCozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {
    private CadastroCozinhaService cadastroService;
    private CozinhaMapper mapper;

    @Autowired
    public CozinhaController(CadastroCozinhaService cadastroService) {
        this.cadastroService = cadastroService;
        this.mapper = CozinhaMapper.getInstance();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CozinhaDTO>> listar() {
        return ResponseEntity.ok(cadastroService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CozinhaDTO> consultar(@PathVariable long id) {
        Optional<CozinhaDTO> cozinha = cadastroService.consultar(id);

        return cozinha.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Optional<CozinhaDTO> salvar(@RequestBody CozinhaDTO cozinha) {
        return cadastroService.cadastrar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<CozinhaDTO> atualizar(@PathVariable long cozinhaId, @RequestBody CozinhaDTO alteracao) {
        Optional<CozinhaDTO> cozinha = cadastroService.alterar(cozinhaId, alteracao);

        return cozinha.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Void> excluir(@PathVariable long cozinhaId) {
        try {
            cadastroService.excluir(cozinhaId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeEmUsoException err) {
            return ResponseEntity.status(CONFLICT).build();

        } catch (EntidadeNaoEncontradaException err) {
            return ResponseEntity.notFound().build();
        }
    }
}
