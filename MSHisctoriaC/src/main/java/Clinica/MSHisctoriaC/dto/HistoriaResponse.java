package Clinica.MSHisctoriaC.dto;

import java.io.Serializable;

public class HistoriaResponse implements Serializable{
    
    private static final long serialVersionUID =1L;

    private final String userName;

    public HistoriaResponse(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    
}
