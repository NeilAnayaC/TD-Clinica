package Clinica.MSHisctoriaC.dto;

import java.io.Serializable;
import java.util.Date;

public class DetalleRequest implements Serializable{
   
    private static final long serialVersionUID = 1L;
    private Integer idhistoria;
    private Date fechadetalle;
    private String descripcion;
    private String resultado;
    private String tratamiento;
    
    public DetalleRequest() {
    }

    public DetalleRequest(Integer idhistoria, Date fechadetalle, String descripcion, String resultado,
            String tratamiento) {
        this.idhistoria = idhistoria;
        this.fechadetalle = fechadetalle;
        this.descripcion = descripcion;
        this.resultado = resultado;
        this.tratamiento = tratamiento;
    }

    public Date getFechadetalle() {
        return fechadetalle;
    }

    public void setFechadetalle(Date fechadetalle) {
        this.fechadetalle = fechadetalle;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Integer getIdhistoria() {
        return idhistoria;
    }

    public void setIdhistoria(Integer idhistoria) {
        this.idhistoria = idhistoria;
    }

    
}
