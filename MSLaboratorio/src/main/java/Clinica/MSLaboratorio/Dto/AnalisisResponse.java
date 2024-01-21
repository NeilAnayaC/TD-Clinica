package Clinica.MSLaboratorio.Dto;

import java.io.Serializable;

public class AnalisisResponse implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private final int diagnosticoid;
    private final String diagnostico;
    private final int pacienteid;
    
    public AnalisisResponse(int diagnosticoid, String diagnostico, int pacienteid) {
        this.diagnosticoid = diagnosticoid;
        this.diagnostico = diagnostico;
        this.pacienteid = pacienteid;
    }

    public int getDiagnosticoid() {
        return diagnosticoid;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public int getPacienteid() {
        return pacienteid;
    }

    
}
