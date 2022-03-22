package br.com.algaworks.algafoodapi.api.mapper;

import br.com.algaworks.algafoodapi.api.response.CozinhaDTO;
import br.com.algaworks.algafoodapi.domain.model.Cozinha;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CozinhaMapper {
    static CozinhaMapper getInstance() {
        return Mappers.getMapper(CozinhaMapper.class);
    }

    List<CozinhaDTO> map(List<Cozinha> cozinha);
    CozinhaDTO toDTO(Cozinha cozinha);
}
