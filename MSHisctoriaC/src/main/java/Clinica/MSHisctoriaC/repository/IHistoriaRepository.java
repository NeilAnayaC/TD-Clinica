package Clinica.MSHisctoriaC.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Clinica.MSHisctoriaC.model.modelHistoria;

@Repository
public interface IHistoriaRepository extends CrudRepository<modelHistoria,Integer>{
    
}
