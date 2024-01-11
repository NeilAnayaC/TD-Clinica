package Clinica.MSPaciente.dto;

import java.io.Serializable;

public class AuthRequest implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String apellido;
    private String genero;
    private String celular;


    public AuthRequest() {
    }


    public AuthRequest(String nombre, String apellido, String genero, String celular) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.celular = celular;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getApellido() {
        return apellido;
    }


    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getGenero() {
        return genero;
    }


    public void setGenero(String genero) {
        this.genero = genero;
    }


    public String getCelular() {
        return celular;
    }


    public void setCelular(String celular) {
        this.celular = celular;
    }

    
    
}
