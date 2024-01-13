package Clinica.MSUsuario.dto;

import java.io.Serializable;

public class AuthRequestUsu implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String usuario;
    private String clave;
    
    public AuthRequestUsu() {
    }

    public AuthRequestUsu(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    
}
