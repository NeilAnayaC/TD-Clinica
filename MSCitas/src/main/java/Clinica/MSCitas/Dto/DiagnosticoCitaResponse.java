package Clinica.MSCitas.Dto;

import java.io.Serializable;

public class DiagnosticoCitaResponse implements Serializable{
    
    private static final long serialVersionUID =1L;
    private final int citasid;
    private final String diagnosticodetalle;
    private final String estado;
    private final int pacienteid;
    
    public DiagnosticoCitaResponse(int citasid, String diagnosticodetalle, String estado, int pacienteid) {
        this.citasid = citasid;
        this.diagnosticodetalle = diagnosticodetalle;
        this.estado = estado;
        this.pacienteid = pacienteid;
    }

    public int getCitasid() {
        return citasid;
    }

    public String getDiagnosticodetalle() {
        return diagnosticodetalle;
    }

    public String getEstado() {
        return estado;
    }

    public int getPacienteid() {
        return pacienteid;
    }

    
}
