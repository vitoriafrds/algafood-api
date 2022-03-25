package br.com.algaworks.algafoodapi.api.controller;

import br.com.algaworks.algafoodapi.api.mapper.CozinhaMapper;
import br.com.algaworks.algafoodapi.api.response.CozinhaDTO;
import br.com.algaworks.algafoodapi.domain.model.Cozinha;
import br.com.algaworks.algafoodapi.domain.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

    private CadastroCozinhaService cadastroCozinhaService;
    private ListagemCozinhasService listagemCozinhasService;
    private ConsultaCozinhaService consultaCozinhaService;
    private AlteracaoCozinhaService alteracaoService;
    private ExclusaoCozinhaService exclusaoService;
    private CozinhaMapper mapper;

    @Autowired
    public CozinhaController(CadastroCozinhaService cadastroService,
                             ListagemCozinhasService listagemService,
                             ConsultaCozinhaService consultaService,
                             AlteracaoCozinhaService alteracaoService,
                             ExclusaoCozinhaService exclusaoService) {
        this.cadastroCozinhaService = cadastroService;
        this.listagemCozinhasService = listagemService;
        this.consultaCozinhaService = consultaService;
        this.alteracaoService = alteracaoService;
        this.exclusaoService = exclusaoService;
        this.mapper = CozinhaMapper.getInstance();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CozinhaDTO>> listar() {
        return ResponseEntity.ok(listagemCozinhasService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CozinhaDTO> consultar(@PathVariable long id) {
        Optional<CozinhaDTO> cozinha = consultaCozinhaService.consultar(id);

        return cozinha.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Optional<CozinhaDTO> salvar(@RequestBody CozinhaDTO cozinha) {
        return cadastroCozinhaService.cadastrar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<CozinhaDTO> atualizar(@PathVariable long cozinhaId, @RequestBody CozinhaDTO alteracao) {
        Optional<CozinhaDTO> cozinha = alteracaoService.alterar(cozinhaId, alteracao);

        return cozinha.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Void> excluir(@PathVariable long cozinhaId) {
        Optional<CozinhaDTO> cozinha = consultaCozinhaService.consultar(cozinhaId);

        if (cozinha.isPresent()) {
            exclusaoService.excluir(mapper.toModel(cozinha.get()));
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
