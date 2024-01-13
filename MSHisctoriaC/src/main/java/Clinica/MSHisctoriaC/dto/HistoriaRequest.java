package Clinica.MSHisctoriaC.dto;

import java.io.Serializable;
import java.util.Date;

public class HistoriaRequest implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private Integer pacienteid;
    private Date fechacreacrion;
    private String medico;
    private String diagnostico;
    
    public HistoriaRequest() {
    }

    public HistoriaRequest(Integer pacienteid, Date fechacreacrion, String medico, String diagnostico) {
        this.pacienteid = pacienteid;
        this.fechacreacrion = fechacreacrion;
        this.medico = medico;
        this.diagnostico = diagnostico;
    }

    public Integer getPacienteid() {
        return pacienteid;
    }

    public void setPacienteid(Integer pacienteid) {
        this.pacienteid = pacienteid;
    }

    public Date getFechacreacrion() {
        return fechacreacrion;
    }

    public void setFechacreacrion(Date fechacreacrion) {
        this.fechacreacrion = fechacreacrion;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    
    

    
}
