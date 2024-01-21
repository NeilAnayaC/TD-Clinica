package Clinica.MSCitas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Clinica.MSCitas.Exception.CitasNotFoundException;
import Clinica.MSCitas.Model.ModelCitas;
import Clinica.MSCitas.Repository.ICitasRepository;

@Service
public class CitasService implements ICitasService{
    
    @Autowired
    ICitasRepository citasRepository;

    @Override
    public ModelCitas crearcita (ModelCitas citas){
        return citasRepository.save(citas);
    }

    @Override
    public List<ModelCitas> listarCita(){
        return (List<ModelCitas>) citasRepository.findAll();
    }

    @Override 
    public ModelCitas buscarIdCita(int id){
        Optional<ModelCitas> citas = citasRepository.findById(id);
        return citas.get();
    }

    @Override 
    public ModelCitas actualizarCita(ModelCitas citas){
        if (citasRepository.existsById(citas.getCitasid())) {
            return citasRepository.save(citas);
        }else {
            throw new CitasNotFoundException("no se puede actualizar Citas");
        }
    }

    @Override
    public boolean deleteCita(int id){
        citasRepository.deleteById(id);
        return true;
    }

}
