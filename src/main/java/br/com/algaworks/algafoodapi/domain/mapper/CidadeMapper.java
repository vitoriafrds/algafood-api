package br.com.algaworks.algafoodapi.domain.mapper;

import br.com.algaworks.algafoodapi.api.response.CidadeDTO;
import br.com.algaworks.algafoodapi.domain.model.Cidade;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CidadeMapper {

    CidadeMapper INSTANCE = Mappers.getMapper(CidadeMapper.class);

    static CidadeMapper get() {
        return INSTANCE;
    }

    Cidade map(CidadeDTO cidade);
    CidadeDTO map(Cidade cidade);
    List<CidadeDTO> map(List<Cidade> cidades);
}
