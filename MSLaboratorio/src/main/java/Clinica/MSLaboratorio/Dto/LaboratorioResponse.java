package Clinica.MSLaboratorio.Dto;

import java.io.Serializable;

public class LaboratorioResponse implements Serializable{
 
    private static final long serialVersionUID = 1L;
    private final int analisisid;
    private final int pacienteid;
    private final String detalleresultado;
    
    public LaboratorioResponse(int analisisid, int pacienteid, String detalleresultado) {
        this.analisisid = analisisid;
        this.pacienteid = pacienteid;
        this.detalleresultado = detalleresultado;
    }

    public int getAnalisisid() {
        return analisisid;
    }

    public int getPacienteid() {
        return pacienteid;
    }

    public String getDetalleresultado() {
        return detalleresultado;
    }

    
}
