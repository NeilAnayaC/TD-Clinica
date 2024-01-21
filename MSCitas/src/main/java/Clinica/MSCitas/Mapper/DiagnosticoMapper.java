package Clinica.MSCitas.Mapper;

import org.springframework.stereotype.Component;

//import Clinica.MSCitas.Dto.CitasRequest;
import Clinica.MSCitas.Dto.DiagnosticoCitaResquets;
//import Clinica.MSCitas.Model.ModelCitas;
import Clinica.MSCitas.Model.ModelDiagnosticoCita;

@Component
public class DiagnosticoMapper {

    public DiagnosticoCitaResquets entityToDto(ModelDiagnosticoCita diagnostico){
        DiagnosticoCitaResquets dto = new DiagnosticoCitaResquets();
        dto.setCitasid(diagnostico.getCitas().getCitasid());
        dto.setDiagnostico(diagnostico.getDiagnostico());
        dto.setEstado(diagnostico.getEstado());
        dto.setPacienteid(diagnostico.getPacienteid());
        return dto;
    }

    public ModelDiagnosticoCita dtoTOEntity(DiagnosticoCitaResquets dto){
        ModelDiagnosticoCita diagnostico = new ModelDiagnosticoCita();
        diagnostico.setDiagnostico(dto.getDiagnostico());
        diagnostico.setEstado(dto.getEstado());
        diagnostico.setPacienteid(dto.getPacienteid());
        return diagnostico;
    }
    

}
