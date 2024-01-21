package Clinica.MSCitas.Dto;

import java.io.Serializable;

public class DiagnosticoCitaResquets implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private int citasid;
    private String diagnostico;
    private String estado;
    private int pacienteid;
    
    public DiagnosticoCitaResquets() {
    }

    public DiagnosticoCitaResquets(int citasid, String diagnostico, String estado, int pacienteid) {
        this.citasid = citasid;
        this.diagnostico = diagnostico;
        this.estado = estado;
        this.pacienteid = pacienteid;
    }

    public int getCitasid() {
        return citasid;
    }

    public void setCitasid(int citasid) {
        this.citasid = citasid;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPacienteid() {
        return pacienteid;
    }

    public void setPacienteid(int pacienteid) {
        this.pacienteid = pacienteid;
    }

    
}
