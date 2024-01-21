package Clinica.MSLaboratorio.Mapper;

import org.springframework.stereotype.Component;

import Clinica.MSLaboratorio.Dto.AnalisisResquest;
import Clinica.MSLaboratorio.Model.ModelAnalisis;

@Component
public class AnalisisMapper {
    

    public AnalisisResquest entityToDto(ModelAnalisis analisis){
        AnalisisResquest dto = new AnalisisResquest();
            dto.setDiagnosticoid(analisis.getDiagnosticoid());
            dto.setDiagnostico(analisis.getDiagnostico());
            dto.setPacienteid(analisis.getPacienteid());
            return dto;
        }
    
        public ModelAnalisis dtoTOEntity(AnalisisResquest dto){
            ModelAnalisis citas = new ModelAnalisis();
            citas.setDiagnosticoid(dto.getDiagnosticoid());
            citas.setDiagnostico(dto.getDiagnostico());
            citas.setPacienteid(dto.getPacienteid());
            return citas;
        }
}
