package Clinica.MSLaboratorio.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Clinica.MSLaboratorio.Exception.LaboratorioNotFoundException;
import Clinica.MSLaboratorio.Model.ModelAnalisis;
import Clinica.MSLaboratorio.Repository.AnalisisRepository;

@Service
public class AnalisisService implements IAnalisisService{
    

    @Autowired
    AnalisisRepository analisisRepository ;

    @Override
    public ModelAnalisis crearAnalisis (ModelAnalisis analisis){
        return analisisRepository.save(analisis);
    }

    @Override
    public List<ModelAnalisis> ListarAnalisis(){
        return (List<ModelAnalisis>) analisisRepository.findAll();
    }

    @Override 
    public ModelAnalisis buscarIdAnalisis(int id){
        Optional<ModelAnalisis> analisis = analisisRepository.findById(id);
        return analisis.get();
    }

    @Override 
    public ModelAnalisis actualizarAnalisis(ModelAnalisis analisis){
        if (analisisRepository.existsById(analisis.getAnalisisid())) {
            return analisisRepository.save(analisis);
        }else {
            throw new LaboratorioNotFoundException("no se puede actualizar Analisis");
        }
    }

    @Override
    public boolean deleteAnalisis(int id){
        analisisRepository.deleteById(id);
        return true;
    }
}
