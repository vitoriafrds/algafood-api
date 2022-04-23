package br.com.algaworks.algafoodapi.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CidadeDTO {
    private long id;
    private String nome;
    private EstadoDTO estado;
}
