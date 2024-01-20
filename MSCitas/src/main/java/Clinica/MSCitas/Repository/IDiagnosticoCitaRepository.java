package Clinica.MSCitas.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Clinica.MSCitas.Model.ModelDiagnosticoCita;

@Repository
public interface IDiagnosticoCitaRepository extends CrudRepository<ModelDiagnosticoCita,Integer>{
    
}
