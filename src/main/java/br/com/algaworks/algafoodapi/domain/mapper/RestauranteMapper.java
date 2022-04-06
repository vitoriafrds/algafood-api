package br.com.algaworks.algafoodapi.domain.mapper;

import br.com.algaworks.algafoodapi.api.response.RestauranteDTO;
import br.com.algaworks.algafoodapi.domain.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RestauranteMapper {
     RestauranteMapper INSTANCE = Mappers.getMapper(RestauranteMapper.class);

     static RestauranteMapper get() {
         return INSTANCE;
     }

     RestauranteDTO toRepresentationModel(Restaurante entity);
     List<RestauranteDTO> toRepresentationModel(List<Restaurante> entity);
}
