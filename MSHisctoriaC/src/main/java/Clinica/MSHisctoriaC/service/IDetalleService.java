package Clinica.MSHisctoriaC.service;

import java.util.List;

import Clinica.MSHisctoriaC.model.modelDetalle;

public interface IDetalleService {
    public modelDetalle add(modelDetalle detalle);
    public List<modelDetalle> findAll();
    public modelDetalle findById(Integer id);
    public modelDetalle update(modelDetalle historia);
}
