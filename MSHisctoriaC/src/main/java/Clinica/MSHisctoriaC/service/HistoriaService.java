package Clinica.MSHisctoriaC.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Clinica.MSHisctoriaC.Exection.HistoriaNotFoundException;
import Clinica.MSHisctoriaC.model.modelHistoria;
import Clinica.MSHisctoriaC.repository.IHistoriaRepository;

@Service
public class HistoriaService implements IHistoriaService{
    
    @Autowired
    IHistoriaRepository historiaRepository;

    @Override
    public modelHistoria add(modelHistoria historia){
       // if(historia != null)
        return historiaRepository.save(historia);
      //  else
       //  return null;
    }

    @Override
    public List<modelHistoria> findAll() {
        return (List<modelHistoria>) historiaRepository.findAll();
    }
    @Override
    public modelHistoria findById(Integer id) {
        Optional<modelHistoria> historias = historiaRepository.findById(id);
        return historias.get();
    }
    @Override
    public modelHistoria update(modelHistoria historia) {

        if (historiaRepository.existsById(historia.getIdhistoria())) {
            return historiaRepository.save(historia);
        }else{
            throw new HistoriaNotFoundException("No se puede actualizar. Historia no encontrado con ID:"+ historia.getIdhistoria());
        }
    }
}
