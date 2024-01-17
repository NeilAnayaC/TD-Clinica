package Clinica.MSHisctoriaC.Mapper;

import org.springframework.stereotype.Component;

import Clinica.MSHisctoriaC.dto.DetalleRequest;
import Clinica.MSHisctoriaC.model.modelDetalle;

@Component
public class DetalleMapper {
    
    public DetalleRequest entityToDto(modelDetalle detalle){
        DetalleRequest dto = new DetalleRequest();
        dto.setFechadetalle(detalle.getFechadetalle());
        dto.setDescripcion(detalle.getDescripcion());
        dto.setResultado(detalle.getResultado());
        dto.setTratamiento(detalle.getTratamiento());
        return dto;
    }

    public modelDetalle dtoTOEntity(DetalleRequest dto){
        modelDetalle detalle = new modelDetalle();
        detalle.setFechadetalle(dto.getFechadetalle());
        detalle.setDescripcion(dto.getDescripcion());
        detalle.setResultado(dto.getResultado());
        detalle.setTratamiento(dto.getTratamiento());
        return detalle;
    }
}
