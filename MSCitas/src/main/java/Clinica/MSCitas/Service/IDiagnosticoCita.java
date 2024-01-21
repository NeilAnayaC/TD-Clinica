package Clinica.MSCitas.Service;

import java.util.List;

//import Clinica.MSCitas.Model.ModelCitas;
import Clinica.MSCitas.Model.ModelDiagnosticoCita;

public interface IDiagnosticoCita {
    public ModelDiagnosticoCita crearDiagnosticocita(ModelDiagnosticoCita diagnostico);
    public List<ModelDiagnosticoCita> listarDiagnosticocita();
    public ModelDiagnosticoCita buscarIdDiagnosticocita(int id);
    public ModelDiagnosticoCita actualizarDiagnosticocita(ModelDiagnosticoCita diagnostico);
    public boolean deleteDiagnosticocita(int id);
}
