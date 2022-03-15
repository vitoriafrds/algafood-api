package br.com.algaworks.algafoodapi.api.controller;

import br.com.algaworks.algafoodapi.api.mapper.CozinhaMapper;
import br.com.algaworks.algafoodapi.api.response.CozinhaDTO;
import br.com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private CozinhaRepository cozinhaRepository;
    private CozinhaMapper mapper;

    public CozinhaController(CozinhaRepository repository) {
        this.cozinhaRepository = repository;
        this.mapper = CozinhaMapper.getInstance();
    }

    @GetMapping
    private List<CozinhaDTO> listar() {
        return mapper.map(cozinhaRepository.listar());
    }
}
