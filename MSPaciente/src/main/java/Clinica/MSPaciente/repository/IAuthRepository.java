package Clinica.MSPaciente.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Clinica.MSPaciente.model.modelPaciente;

@Repository
public interface IAuthRepository extends CrudRepository<modelPaciente, Integer>{
    
}
