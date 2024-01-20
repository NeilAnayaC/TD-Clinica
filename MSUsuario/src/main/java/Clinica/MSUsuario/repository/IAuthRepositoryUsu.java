package Clinica.MSUsuario.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Clinica.MSUsuario.model.modelUsuario;

@Repository
public interface IAuthRepositoryUsu extends CrudRepository<modelUsuario, Integer>{
}
