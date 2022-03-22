package br.com.algaworks.algafoodapi.api.model;

import br.com.algaworks.algafoodapi.api.response.CozinhaDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "cozinhas")
public class CozinhasXMLWrapper {

    @NonNull
    @JsonProperty("cozinha")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CozinhaDTO> cozinhas;
}
