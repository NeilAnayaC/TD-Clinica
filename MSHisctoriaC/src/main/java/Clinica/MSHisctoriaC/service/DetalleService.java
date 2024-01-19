package Clinica.MSHisctoriaC.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Clinica.MSHisctoriaC.Exection.HistoriaNotFoundException;
import Clinica.MSHisctoriaC.model.modelDetalle;
import Clinica.MSHisctoriaC.repository.IDetalleRepository;

@Service
public class DetalleService implements IDetalleService{
    

    @Autowired
    IDetalleRepository detalleRepository;

    @Override 
    public modelDetalle add (modelDetalle detalle){
        return detalleRepository.save(detalle);
    }

    @Override
    public List<modelDetalle> findAll(){
        return (List<modelDetalle>) detalleRepository.findAll();
    }

    @Override
    public modelDetalle findById(Integer id){
        Optional<modelDetalle> detalles = detalleRepository.findById(id);
        return detalles.get();
    }

    @Override
    public modelDetalle update(modelDetalle detalle){
        if (detalleRepository.existsById(detalle.getIddetalle())) {
            return detalleRepository.save(detalle);
        }else{
            throw new HistoriaNotFoundException("No se puede actualizar. Historia no encontrado con ID:"+ detalle.getIddetalle());
        }
    }

}
