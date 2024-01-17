package Clinica.MSHisctoriaC.Exection;

public class ApiResponseH<T> {
    private final int status;
    private final String mensaje;
    private final T data;
    
    public ApiResponseH(int status, String mensaje, T data) {
        this.status = status;
        this.mensaje = mensaje;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public T getData() {
        return data;
    }
    
}
