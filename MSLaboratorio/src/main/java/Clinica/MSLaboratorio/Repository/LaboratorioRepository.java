package Clinica.MSLaboratorio.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Clinica.MSLaboratorio.Model.ModelLaboratorio;

@Repository
public interface LaboratorioRepository extends CrudRepository<ModelLaboratorio, Integer>{

    
}
