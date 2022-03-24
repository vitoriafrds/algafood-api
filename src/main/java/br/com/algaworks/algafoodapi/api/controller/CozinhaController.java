package br.com.algaworks.algafoodapi.api.controller;

import br.com.algaworks.algafoodapi.api.mapper.CozinhaMapper;
import br.com.algaworks.algafoodapi.api.model.CozinhasXMLWrapper;
import br.com.algaworks.algafoodapi.api.response.CozinhaDTO;
import br.com.algaworks.algafoodapi.domain.model.Cozinha;
import br.com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

    private CozinhaRepository cozinhaRepository;
    private CozinhaMapper mapper;

    public CozinhaController(CozinhaRepository repository) {
        this.cozinhaRepository = repository;
        this.mapper = CozinhaMapper.getInstance();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CozinhaDTO>> listarJson() {
        return ResponseEntity.ok(mapper.map(cozinhaRepository.listar()));
    }

    @GetMapping(produces = APPLICATION_XML_VALUE)
    public ResponseEntity<CozinhasXMLWrapper> listarXML() {
        return ResponseEntity.ok(new CozinhasXMLWrapper(mapper.map(cozinhaRepository.listar())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CozinhaDTO> consultar(@PathVariable long id) {

        Cozinha cozinha = cozinhaRepository.buscar(id);

        if (cozinha != null) {
            return ResponseEntity.ok(mapper.toDTO(cozinhaRepository.buscar(id)));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public CozinhaDTO salvar(@RequestBody CozinhaDTO cozinha) {
        Cozinha cozinhaRegistrada = cozinhaRepository.salvar(mapper.toModel(cozinha));
        return mapper.toDTO(cozinhaRegistrada);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<CozinhaDTO> atualizar(@PathVariable long cozinhaId, @RequestBody CozinhaDTO alteracao) {
        Cozinha cozinha = cozinhaRepository.atualizar(cozinhaId, mapper.toModel(alteracao));

        if (cozinha != null) {
            return ResponseEntity.ok(mapper.toDTO(cozinha));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Void> excluir(@PathVariable long cozinhaId) {
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if (cozinha != null) {
            cozinhaRepository.remover(cozinha);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();

    }
}
