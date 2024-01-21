package Clinica.MSCitas.Service;

import java.util.List;

import Clinica.MSCitas.Model.ModelCitas;

public interface ICitasService {
    
    public ModelCitas crearcita(ModelCitas citas);
    public List<ModelCitas> listarCita();
    public ModelCitas buscarIdCita(int id);
    public ModelCitas actualizarCita(ModelCitas citas);
    public boolean deleteCita(int id);
}
