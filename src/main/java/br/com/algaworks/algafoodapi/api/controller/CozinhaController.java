package br.com.algaworks.algafoodapi.api.controller;

import br.com.algaworks.algafoodapi.api.mapper.CozinhaMapper;
import br.com.algaworks.algafoodapi.api.model.CozinhasXMLWrapper;
import br.com.algaworks.algafoodapi.api.response.CozinhaDTO;
import br.com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        return ResponseEntity.ok(mapper.toDTO(cozinhaRepository.buscar(id)));
    }
}
