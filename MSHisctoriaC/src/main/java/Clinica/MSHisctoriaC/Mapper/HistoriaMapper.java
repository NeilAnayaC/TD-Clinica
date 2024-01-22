package Clinica.MSHisctoriaC.Mapper;

import org.springframework.stereotype.Component;

import Clinica.MSHisctoriaC.dto.HistoriaRequest;
import Clinica.MSHisctoriaC.model.modelHistoria;

@Component
public class HistoriaMapper {
    public HistoriaRequest entityToDto(modelHistoria historia){
        HistoriaRequest dto = new HistoriaRequest();
        dto.setPacienteid(historia.getPacienteid());
        dto.setFechacreacrion(historia.getFechacreacion());
        dto.setMedico(historia.getMedico());
        dto.setDiagnostico(historia.getDiagnostico());
        return dto;
    }

    public modelHistoria dtoTOEntity(HistoriaRequest dto){
        modelHistoria historia = new modelHistoria();
        historia.setPacienteid(dto.getPacienteid());
        historia.setFechacreacion(dto.getFechacreacrion());
        historia.setMedico(dto.getMedico());
        historia.setDiagnostico(dto.getDiagnostico());
        return historia;
    }
}
