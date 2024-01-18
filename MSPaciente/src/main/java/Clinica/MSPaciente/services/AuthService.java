package Clinica.MSPaciente.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import Clinica.MSPaciente.Redis.DBcache;
import Clinica.MSPaciente.model.modelPaciente;
import Clinica.MSPaciente.repository.IAuthRepository;

@Service
public class AuthService implements IAuthServices{
    
    @Autowired
    IAuthRepository authRepository;

    @Override
    public modelPaciente add(modelPaciente paciente){
        return authRepository.save(paciente);
    }

    @Override
    public modelPaciente update(modelPaciente paciente){
        return authRepository.save(paciente);
    }

    @Override
    public boolean delete(int id){
        authRepository.deleteById(id);
        return true;
    }

    @Override
    @Cacheable(value = DBcache.CACHE_NAME)
    public List<modelPaciente> findAll(){
        return(List<modelPaciente>) authRepository.findAll();
    }

    @Override
    public modelPaciente findById(int id){
        Optional<modelPaciente> model = authRepository.findById(id);
        return model.get();
    }

    @Override
    @Cacheable(value = DBcache.CACHE_NAME)
    public List<modelPaciente> findAllRedis(){
        return(List<modelPaciente>) authRepository.findAll();
    }
}
