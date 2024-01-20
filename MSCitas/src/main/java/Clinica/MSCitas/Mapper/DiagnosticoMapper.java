package Clinica.MSCitas.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import Clinica.MSCitas.Dto.DiagnosticoCitaResponse;
import Clinica.MSCitas.Dto.DiagnosticoCitaResquets;
import Clinica.MSCitas.Model.ModelDiagnosticoCita;

@Mapper
public interface DiagnosticoMapper {
    
    DiagnosticoMapper INSTANCE = Mappers.getMapper(DiagnosticoMapper.class);
    ModelDiagnosticoCita toEntity(DiagnosticoCitaResquets dto);
    DiagnosticoCitaResponse toDto(ModelDiagnosticoCita entity);
    List<DiagnosticoCitaResponse> toDtoList(List<ModelDiagnosticoCita> entities);
}
