package Clinica.MSHisctoriaC.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Clinica.MSHisctoriaC.model.modelDetalle;

@Repository
public interface IDetalleRepository extends CrudRepository<modelDetalle, Integer>{
    
}
