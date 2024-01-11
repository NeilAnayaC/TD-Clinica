package Clinica.MSPaciente.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Clinica.MSPaciente.model.modelPaciente;
import Clinica.MSPaciente.repository.IAuthRepository;

@Service
public class AuthService implements IAuthServices{
    
    @Autowired
    IAuthRepository authRepository;

    @Override
    public modelPaciente add(modelPaciente paci){
        return authRepository.save(paci);
    }

    @Override
    public modelPaciente update(modelPaciente paci){
        return authRepository.save(paci);
    }

    @Override
    public boolean delete(int id){
        authRepository.deleteById(id);
        return true;
    }

    @Override
    public List<modelPaciente> findAll(){
        return(List<modelPaciente>) authRepository.findAll();
    }

    @Override
    public modelPaciente findById(int id){
        Optional<modelPaciente> model = authRepository.findById(id);
        return model.get();
    }
}
