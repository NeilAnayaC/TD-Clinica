package Clinica.MSHisctoriaC.service;

import java.util.List;

import Clinica.MSHisctoriaC.model.modelHistoria;

public interface IHistoriaService {
    
    public modelHistoria add (modelHistoria historia);
    public List<modelHistoria> findAll();
    public modelHistoria findById(Integer id);
    public modelHistoria update(modelHistoria historia);
}
