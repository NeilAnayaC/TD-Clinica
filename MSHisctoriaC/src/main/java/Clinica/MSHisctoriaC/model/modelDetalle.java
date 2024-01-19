package Clinica.MSHisctoriaC.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle")
public class modelDetalle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetalle")
    private Integer iddetalle;

    @ManyToOne
    @JoinColumn(name = "idhistoria")
    private modelHistoria historia;

    @Column(name = "fechadetalle")
    private Date fechadetalle;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "resultado") 
    private String resultado;

    @Column(name = "tratamiento")
    private String tratamiento;

    public Integer getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Integer iddetalle) {
        this.iddetalle = iddetalle;
    }

    public modelHistoria getHistoria() {
        return historia;
    }

    public void setHistoria(modelHistoria historia) {
        this.historia = historia;
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

}
