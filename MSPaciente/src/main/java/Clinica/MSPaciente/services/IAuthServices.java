package Clinica.MSPaciente.services;

import java.util.List;

import Clinica.MSPaciente.model.modelPaciente;

public interface IAuthServices {
    
    public modelPaciente add (modelPaciente paciente);
    public modelPaciente update (modelPaciente paciente);
    public boolean delete (int id);
    public List<modelPaciente> findAll();
    public modelPaciente findById(int id);
    public List<modelPaciente> findAllRedis();
}
