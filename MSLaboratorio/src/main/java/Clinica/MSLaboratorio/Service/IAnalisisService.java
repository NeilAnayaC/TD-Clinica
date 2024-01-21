package Clinica.MSLaboratorio.Service;

import java.util.List;

import Clinica.MSLaboratorio.Model.ModelAnalisis;

public interface IAnalisisService {

    public ModelAnalisis crearAnalisis(ModelAnalisis citas);
    public List<ModelAnalisis> ListarAnalisis();
    public ModelAnalisis buscarIdAnalisis(int id);
    public ModelAnalisis actualizarAnalisis(ModelAnalisis citas);
    public boolean deleteAnalisis(int id);
}
