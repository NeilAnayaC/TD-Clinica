package Clinica.MSCitas.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import Clinica.MSCitas.Dto.CitasRequest;
import Clinica.MSCitas.Dto.CitasResponse;
import Clinica.MSCitas.Model.ModelCitas;

@Mapper
public interface CitasMapper {
    CitasMapper INTANCE = Mappers.getMapper(CitasMapper.class);
    ModelCitas toEntity(CitasRequest dto);
    CitasResponse toDto(ModelCitas entiity);
    List<CitasResponse> toDtoList(List<ModelCitas> entities);
}
