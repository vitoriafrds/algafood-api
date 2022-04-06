package br.com.algaworks.algafoodapi.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class RestauranteDTO {
    private long id;
    private String nome;

    @JsonProperty("taxa_frete")
    private BigDecimal taxaFrete;
}
