package Clinica.MSCitas.Dto;

import java.io.Serializable;
import java.util.Date;

public class CitasRequest implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private int pacienteid;
    private int doctorid;
    private Date fecha;
    
    public CitasRequest() {
    }

    public CitasRequest(int pacienteid, int doctorid, Date fecha) {
        this.pacienteid = pacienteid;
        this.doctorid = doctorid;
        this.fecha = fecha;
    }

    public int getPacienteid() {
        return pacienteid;
    }

    public void setPacienteid(int pacienteid) {
        this.pacienteid = pacienteid;
    }

    public int getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(int doctorid) {
        this.doctorid = doctorid;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
}
