package Clinica.MSUsuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Clinica.MSUsuario.model.modelUsuario;
import Clinica.MSUsuario.repository.IAuthRepositoryUsu;

@Service
public class AuthServiceUsu implements IAuthServiceUsu{
    
    @Autowired
    IAuthRepositoryUsu authRepositoryUsu;

    @Override
    public modelUsuario add(modelUsuario usu){
        return authRepositoryUsu.save(usu);
    }

    @Override
    public modelUsuario update(modelUsuario usu){
        return authRepositoryUsu.save(usu);
    }

    @Override
    public boolean delete(int id){
        authRepositoryUsu.deleteById(id);
        return true;
    }

    @Override
    public List<modelUsuario> findAll(){
        return(List<modelUsuario>) authRepositoryUsu.findAll();
    }

    @Override
    public modelUsuario findById(int id){
        Optional<modelUsuario> model = authRepositoryUsu.findById(id);
        return model.get();
    }
}
