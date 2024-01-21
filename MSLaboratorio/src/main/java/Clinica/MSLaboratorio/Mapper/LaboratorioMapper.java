package Clinica.MSLaboratorio.Mapper;

import org.springframework.stereotype.Component;

import Clinica.MSLaboratorio.Dto.LaboratorioRequest;
import Clinica.MSLaboratorio.Model.ModelLaboratorio;

@Component
public class LaboratorioMapper {
    
    public LaboratorioRequest entityToDto(ModelLaboratorio laboratorio){
        LaboratorioRequest dto = new LaboratorioRequest();
            dto.setAnalisisid(laboratorio.getAnalisis().getAnalisisid());
            dto.setPacienteid(laboratorio.getPacienteid());
            dto.setDetalleresultado(laboratorio.getDetalleresultado());
            return dto;
        }
    
        public ModelLaboratorio dtoTOEntity(LaboratorioRequest dto){
            ModelLaboratorio laboratorio = new ModelLaboratorio();
            laboratorio.setPacienteid(dto.getPacienteid());
            laboratorio.setDetalleresultado(dto.getDetalleresultado());
            return laboratorio;
        }
}
