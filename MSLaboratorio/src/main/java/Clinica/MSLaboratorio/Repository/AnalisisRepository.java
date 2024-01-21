package Clinica.MSLaboratorio.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Clinica.MSLaboratorio.Model.ModelAnalisis;

@Repository
public interface AnalisisRepository extends CrudRepository<ModelAnalisis, Integer>{

    
}
