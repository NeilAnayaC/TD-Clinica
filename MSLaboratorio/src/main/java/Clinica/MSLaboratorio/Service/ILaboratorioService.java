package Clinica.MSLaboratorio.Service;

import java.util.List;

import Clinica.MSLaboratorio.Model.ModelLaboratorio;

public interface ILaboratorioService {

    public ModelLaboratorio crearLaboratorio(ModelLaboratorio citas);
    public List<ModelLaboratorio> listarLaboratorios();
    public ModelLaboratorio buscarIdLaboratorio(int id);
    public ModelLaboratorio actualizarLaboratorio(ModelLaboratorio citas);
    public boolean deleteLaboratorios(int id);
}
