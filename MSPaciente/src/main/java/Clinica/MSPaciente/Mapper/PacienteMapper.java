package Clinica.MSPaciente.Mapper;

import org.springframework.stereotype.Component;

import Clinica.MSPaciente.dto.AuthRequest;
import Clinica.MSPaciente.model.modelPaciente;

@Component
public class PacienteMapper {
    public AuthRequest entityToDto(modelPaciente paciente){
        AuthRequest dto = new AuthRequest();
        dto.setNombre(paciente.getNombre());
        dto.setApellido(paciente.getApellido());
        dto.setGenero(paciente.getGenero());
        dto.setCelular(paciente.getCelular());
        return dto;
    }

    public modelPaciente dtoTOEntity(AuthRequest dto){
        modelPaciente paciente = new modelPaciente();
        paciente.setNombre(dto.getNombre());
        paciente.setApellido(dto.getApellido());
        paciente.setGenero(dto.getGenero());
        paciente.setCelular(dto.getCelular());
        return paciente;
    }
}
