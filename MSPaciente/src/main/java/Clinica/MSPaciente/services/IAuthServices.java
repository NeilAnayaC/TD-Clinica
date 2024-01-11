package Clinica.MSPaciente.services;

import java.util.List;

import Clinica.MSPaciente.model.modelPaciente;

public interface IAuthServices {
    
    public modelPaciente add (modelPaciente paci);
    public modelPaciente update (modelPaciente paci);
    public boolean delete (int id);
    public List<modelPaciente> findAll();
    public modelPaciente findById(int id);
}
