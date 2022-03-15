package br.com.algaworks.algafoodapi.api.mapper;

import br.com.algaworks.algafoodapi.api.response.EstadoDTO;
import br.com.algaworks.algafoodapi.domain.model.Estado;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EstadoMapper {
    static EstadoMapper get() {
        return Mappers.getMapper(EstadoMapper.class);
    }

    List<EstadoDTO> map(List<Estado> estados);
    EstadoDTO map(Estado estado);
}
