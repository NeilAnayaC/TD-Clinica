package Clinica.MSUsuario.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import Clinica.MSUsuario.model.modelUsuario;
import Clinica.MSUsuario.redis.DBCache;
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
    @Cacheable(value = DBCache.CACHE_NAME)
    public List<modelUsuario> findAll(){
        return(List<modelUsuario>) authRepositoryUsu.findAll();
    }

    @Override
    public modelUsuario findById(int id){
        Optional<modelUsuario> model = authRepositoryUsu.findById(id);
        return model.get();
    }

    @Override
    public boolean validarCredenciales(String usuario, String clave){
        List<modelUsuario> result = (List<modelUsuario>) authRepositoryUsu.findAll();
        List<modelUsuario> resultFilter = result.stream()
        .filter(t -> t.getUsuario().equals(usuario) && t.getClave().equals(clave))
        .collect(Collectors.toList());
        if(null == resultFilter || resultFilter.isEmpty()){
            return false;
        }
        return true;
    }
}
