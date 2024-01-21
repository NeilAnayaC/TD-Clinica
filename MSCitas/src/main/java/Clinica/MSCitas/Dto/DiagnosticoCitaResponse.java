package Clinica.MSCitas.Dto;

import java.io.Serializable;

public class DiagnosticoCitaResponse implements Serializable{
    
    private static final long serialVersionUID =1L;
    private final int citasid;
    private final String diagnostico;
    private final String estado;
    private final int pacienteid;
    
    public DiagnosticoCitaResponse(int citasid, String diagnostico, String estado, int pacienteid) {
        this.citasid = citasid;
        this.diagnostico = diagnostico;
        this.estado = estado;
        this.pacienteid = pacienteid;
    }

    public int getCitasid() {
        return citasid;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getEstado() {
        return estado;
    }

    public int getPacienteid() {
        return pacienteid;
    }

    
}
