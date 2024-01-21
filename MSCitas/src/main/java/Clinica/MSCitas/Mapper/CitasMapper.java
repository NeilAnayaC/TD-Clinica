package Clinica.MSCitas.Mapper;

import org.springframework.stereotype.Component;

import Clinica.MSCitas.Dto.CitasRequest;

import Clinica.MSCitas.Model.ModelCitas;

@Component
public class CitasMapper {
    public CitasRequest entityToDto(ModelCitas citas){
    CitasRequest dto = new CitasRequest();
        dto.setPacienteid(citas.getPacienteid());
        dto.setDoctorid(citas.getDoctorid());
        dto.setFecha(citas.getFecha());
        return dto;
    }

    public ModelCitas dtoTOEntity(CitasRequest dto){
        ModelCitas citas = new ModelCitas();
        citas.setPacienteid(dto.getPacienteid());
        citas.setDoctorid(dto.getDoctorid());
        citas.setFecha(dto.getFecha());
        return citas;
    }
}
