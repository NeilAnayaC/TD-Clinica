package Clinica.MSCitas.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Clinica.MSCitas.Model.ModelCitas;

@Repository
public interface ICitasRepository extends CrudRepository<ModelCitas,Integer>{
    
}
