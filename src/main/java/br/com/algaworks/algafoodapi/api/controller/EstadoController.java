package br.com.algaworks.algafoodapi.api.controller;

import br.com.algaworks.algafoodapi.api.mapper.EstadoMapper;
import br.com.algaworks.algafoodapi.api.response.EstadoDTO;
import br.com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.GeneratedValue;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private EstadoRepository repository;
    private EstadoMapper mapper;

    @Autowired
    public EstadoController(EstadoRepository repository) {
        this.repository = repository;
        this.mapper = EstadoMapper.get();
    }

    @GetMapping
    public List<EstadoDTO> listar() {
        return mapper.map(repository.listar());
    }
}
