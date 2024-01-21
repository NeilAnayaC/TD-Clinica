package Clinica.MSLaboratorio.Dto;

import java.io.Serializable;

public class AnalisisResquest implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private int diagnosticoid;
    private String diagnostico;
    private int pacienteid;
    
    public AnalisisResquest() {
    }

    public AnalisisResquest(int diagnosticoid, String diagnostico, int pacienteid) {
        this.diagnosticoid = diagnosticoid;
        this.diagnostico = diagnostico;
        this.pacienteid = pacienteid;
    }

    public int getDiagnosticoid() {
        return diagnosticoid;
    }

    public void setDiagnosticoid(int diagnosticoid) {
        this.diagnosticoid = diagnosticoid;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public int getPacienteid() {
        return pacienteid;
    }

    public void setPacienteid(int pacienteid) {
        this.pacienteid = pacienteid;
    }
    
    
    
}
