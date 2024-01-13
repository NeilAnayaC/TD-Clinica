package Clinica.MSUsuario.dto;

import java.io.Serializable;

public class AuthResponseUsu implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private final String userName;

    public AuthResponseUsu(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
    
}
