package Clinica.MSHisctoriaC.message;

public class MensajesParametrizados {
    
    //public static final String MENSAJE_AUTENTICACION_EXITOSA = "Autenticación exitosa. Token:";
    //public static final String MENSAJE_ERROR_AUTENTICACION = "Error de autenticación. Verifica tu nombre de usuario y contraseña.";
    public static final String MENSAJE_CREAR_HISTORIA_EXITOSO = "MSHISTORIA PACIENTE creado exitosamente.";
    public static final String MENSAJE_CREAR_HISTORIA_NOEXITOSO = "MSHISTORIA PACIENTE NO CREADO.";
    public static final String MENSAJE_HISTORIA_EXISTENTE = "MSHISTORIA - El PACIENTE ya existe";
    public static final String MENSAJE_HISTORIA_LISTADO = "MSHISTORIA - LISTADO EXITOSAMENTE";
    public static final String MENSAJE_HISTORIA_LISTADOID = "MSHISTORIA - BUSQUEDA ID EXITOSAMENTE";
    public static final String MENSAJE_HISTORIA_NO_ENCONTRADO = "MSHISTORIA - PACIENTE no encontrado:";
    public static final String MENSAJE_HISTORIA_NO_ENCONTRADOELIMINAR = "MSHISTORIA - PACIENTE no encontrado a eliminar:";
    public static final String MENSAJE_HISTORIA_NO_ENCONTRADO_ID = "MSHISTORIA -PACIENTE no encontrado: ";
    public static final String MENSAJE_HISTORIA_EDITADO_EXITOSO = "MSHISTORIA - PACIENTE editado exitosamente";
    public static final String MENSAJE_ERROR_BASE_DATOS = "MSHISTORIA - Error en la base de datos: ";
    public static final String MENSAJE_ERROR_INTERNO_SERVIDOR = "MSHISTORIA - Error interno del servidor: ";
    public static final String MENSAJE_ELIMINAR_HISTORIA_EXITOSO = "MSHISTORIA - PACIENTE eliminado exitosamente";
    public static final String MENSAJE_ERROR = "MSHISTORIA - Error ";

    public static String usuarioNoEncontrado(String nombreUsuario) {
        return String.format(MENSAJE_HISTORIA_NO_ENCONTRADO, nombreUsuario);
    }
    public static String usuarioNoEncontradoPorId(int idUsuario) {
        return MENSAJE_HISTORIA_NO_ENCONTRADO_ID.replace("{}", String.valueOf(idUsuario));
    }

    public static String errorBaseDatos(String mensajeError) {
        return MENSAJE_ERROR_BASE_DATOS.replace("{}", mensajeError);
    }

    public static String errorServidor(String mensajeError) {
        return MENSAJE_ERROR_INTERNO_SERVIDOR.replace("{}", mensajeError);
    }
}
