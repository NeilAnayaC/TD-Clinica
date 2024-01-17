package Clinica.MSUsuario.dto;

import java.io.Serializable;

public class AuthResponseUsu implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private final String token;
    private final String userName;
    private final String accessTokenExpiration;
    public AuthResponseUsu(String token, String userName, String accessTokenExpiration) {
        this.token = token;
        this.userName = userName;
        this.accessTokenExpiration = accessTokenExpiration;
    }
    public String getToken() {
        return token;
    }
    public String getUserName() {
        return userName;
    }
    public String getAccessTokenExpiration() {
        return accessTokenExpiration;
    }

    
    
}
