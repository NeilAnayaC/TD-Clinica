package Clinica.MSLaboratorio.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Clinica.MSLaboratorio.Exception.LaboratorioNotFoundException;
import Clinica.MSLaboratorio.Model.ModelLaboratorio;
import Clinica.MSLaboratorio.Repository.LaboratorioRepository;

@Service
public class LaboratorioService implements ILaboratorioService{
    

    @Autowired
    LaboratorioRepository laboratorioRepository;

    @Override
    public ModelLaboratorio crearLaboratorio (ModelLaboratorio laboratorio){
        return laboratorioRepository.save(laboratorio);
    }

    @Override
    public List<ModelLaboratorio> listarLaboratorios(){
        return (List<ModelLaboratorio>) laboratorioRepository.findAll();
    }

    @Override 
    public ModelLaboratorio buscarIdLaboratorio(int id){
        Optional<ModelLaboratorio> laboratorio = laboratorioRepository.findById(id);
        return laboratorio.get();
    }

    @Override 
    public ModelLaboratorio actualizarLaboratorio(ModelLaboratorio laboratorio){
        if (laboratorioRepository.existsById(laboratorio.getResultadoid())) {
            return laboratorioRepository.save(laboratorio);
        }else {
            throw new LaboratorioNotFoundException("no se puede actualizar Resultados");
        }
    }

    @Override
    public boolean deleteLaboratorios(int id){
        laboratorioRepository.deleteById(id);
        return true;
    }
}
