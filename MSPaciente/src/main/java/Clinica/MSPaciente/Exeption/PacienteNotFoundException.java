package Clinica.MSPaciente.Exeption;

public class PacienteNotFoundException extends RuntimeException{
    public PacienteNotFoundException(String message){
        super(message);
    }
}
