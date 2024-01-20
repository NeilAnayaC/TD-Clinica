package Clinica.MSCitas.Dto;

import java.io.Serializable;
import java.util.Date;

public class CitasResponse implements Serializable{
    
    private static final long serialVersionUID =1L;
    private final int pacienteid;
    private final int doctorid;
    private final Date fecha;
    
    public CitasResponse(int pacienteid, int doctorid, Date fecha) {
        this.pacienteid = pacienteid;
        this.doctorid = doctorid;
        this.fecha = fecha;
    }

    public int getPacienteid() {
        return pacienteid;
    }

    public int getDoctorid() {
        return doctorid;
    }

    public Date getFecha() {
        return fecha;
    }

    

}
