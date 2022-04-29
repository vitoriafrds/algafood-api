package br.com.algaworks.algafoodapi.api.controller;

import br.com.algaworks.algafoodapi.api.response.CidadeDTO;
import br.com.algaworks.algafoodapi.api.response.CidadeDTO;
import br.com.algaworks.algafoodapi.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CadastroCidadeService cidadeService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CidadeDTO>> listar() {
        return ResponseEntity.ok(cidadeService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CidadeDTO> consultar(@PathVariable long id) {
        Optional<CidadeDTO> cozinha = cidadeService.consultar(id);

        return cozinha.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Optional<CidadeDTO> salvar(@RequestBody CidadeDTO cozinha) {
        return cidadeService.cadastrar(cozinha);
    }

}
