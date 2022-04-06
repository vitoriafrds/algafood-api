package br.com.algaworks.algafoodapi.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/estados", produces = APPLICATION_JSON_VALUE)
public class EstadoController {

}
