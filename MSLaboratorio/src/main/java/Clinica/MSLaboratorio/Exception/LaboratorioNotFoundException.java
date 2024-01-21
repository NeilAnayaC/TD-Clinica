package Clinica.MSLaboratorio.Exception;

public class LaboratorioNotFoundException extends RuntimeException{
    
    public LaboratorioNotFoundException(String message){
        super(message);
    }  
}
