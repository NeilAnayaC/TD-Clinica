package Clinica.MSHisctoriaC.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "historia")
public class modelHistoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhistoria")
    private Integer idhistoria;

    @Column(name = "pacienteid")
    private Integer pacienteid;

    @Column(name = "fechacreacion")
    private Date fechacreacion;

    @Column(name = "medico")
    private String medico;

    @Column(name = "diagnostico")
    private String diagnostio;

    public Integer getIdhistoria() {
        return idhistoria;
    }

    public void setIdhistoria(Integer idhistoria) {
        this.idhistoria = idhistoria;
    }

    public Integer getPacienteid() {
        return pacienteid;
    }

    public void setPacienteid(Integer pacienteid) {
        this.pacienteid = pacienteid;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getDiagnostio() {
        return diagnostio;
    }

    public void setDiagnostio(String diagnostio) {
        this.diagnostio = diagnostio;
    }

    
    

}
