package Clinica.MSCitas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Clinica.MSCitas.Exception.CitasNotFoundException;
//import Clinica.MSCitas.Model.ModelCitas;
import Clinica.MSCitas.Model.ModelDiagnosticoCita;
//import Clinica.MSCitas.Repository.ICitasRepository;
import Clinica.MSCitas.Repository.IDiagnosticoCitaRepository;

@Service
public class DiagnosticoCitaService implements IDiagnosticoCita{
    
    @Autowired
    IDiagnosticoCitaRepository diagnosticoCitaRepository;

    @Override
    public ModelDiagnosticoCita crearDiagnosticocita (ModelDiagnosticoCita diagnostico){
        return diagnosticoCitaRepository.save(diagnostico);
    }

    @Override
    public List<ModelDiagnosticoCita> listarDiagnosticocita(){
        return (List<ModelDiagnosticoCita>) diagnosticoCitaRepository.findAll();
    }

    @Override 
    public ModelDiagnosticoCita buscarIdDiagnosticocita(int id){
        Optional<ModelDiagnosticoCita> diagnostico = diagnosticoCitaRepository.findById(id);
        return diagnostico.get();
    }

    @Override 
    public ModelDiagnosticoCita actualizarDiagnosticocita(ModelDiagnosticoCita diagnostico){
        if (diagnosticoCitaRepository.existsById(diagnostico.getDiagnosticoid())) {
            return diagnosticoCitaRepository.save(diagnostico);
        }else {
            throw new CitasNotFoundException("no se puede actualizar Citas");
        }
    }

    @Override
    public boolean deleteDiagnosticocita(int id){
        diagnosticoCitaRepository.deleteById(id);
        return true;
    }

    @Override
    public ModelDiagnosticoCita updatekafka(ModelDiagnosticoCita diagnostico){
        return diagnosticoCitaRepository.save(diagnostico);
    }
}
