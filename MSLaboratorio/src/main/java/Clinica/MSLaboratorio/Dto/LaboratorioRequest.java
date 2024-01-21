package Clinica.MSLaboratorio.Dto;

import java.io.Serializable;

public class LaboratorioRequest implements Serializable{
    private static final long serialVersionUID = 1L;
    private int analisisid;
    private int pacienteid;
    private String detalleresultado;
    
    public LaboratorioRequest() {
    }

    public LaboratorioRequest(int analisisid, int pacienteid, String detalleresultado) {
        this.analisisid = analisisid;
        this.pacienteid = pacienteid;
        this.detalleresultado = detalleresultado;
    }

    public int getAnalisisid() {
        return analisisid;
    }

    public void setAnalisisid(int analisisid) {
        this.analisisid = analisisid;
    }

    public int getPacienteid() {
        return pacienteid;
    }

    public void setPacienteid(int pacienteid) {
        this.pacienteid = pacienteid;
    }

    public String getDetalleresultado() {
        return detalleresultado;
    }

    public void setDetalleresultado(String detalleresultado) {
        this.detalleresultado = detalleresultado;
    }

    
}
