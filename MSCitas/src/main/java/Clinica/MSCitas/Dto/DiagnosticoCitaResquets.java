package Clinica.MSCitas.Dto;

import java.io.Serializable;

public class DiagnosticoCitaResquets implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private int citasid;
    private String diagnosticodetalle;
    private String estado;
    private int pacienteid;
    
    public DiagnosticoCitaResquets() {
    }

    public DiagnosticoCitaResquets(int citasid, String diagnosticodetalle, String estado, int pacienteid) {
        this.citasid = citasid;
        this.diagnosticodetalle = diagnosticodetalle;
        this.estado = estado;
        this.pacienteid = pacienteid;
    }

    public int getCitasid() {
        return citasid;
    }

    public void setCitasid(int citasid) {
        this.citasid = citasid;
    }

    public String getDiagnosticodetalle() {
        return diagnosticodetalle;
    }

    public void setDiagnosticodetalle(String diagnosticodetalle) {
        this.diagnosticodetalle = diagnosticodetalle;
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
