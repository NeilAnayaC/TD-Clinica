package Clinica.MSPaciente.dto;

import java.io.Serializable;

public class AuthResponse implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private final String userName;

    public AuthResponse(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    

}
