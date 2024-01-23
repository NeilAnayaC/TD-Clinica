package Clinica.MSUsuario.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Clinica.MSUsuario.Exception.ApiResponseUsu;
import Clinica.MSUsuario.Exception.UsuarioNotFoundException;
import Clinica.MSUsuario.Mapper.UsuarioMapper;
import Clinica.MSUsuario.Message.MensajesParametrizados;
import Clinica.MSUsuario.dto.AuthRequestUsu;
import Clinica.MSUsuario.dto.AuthResponseUsu;
import Clinica.MSUsuario.jwt.JwtToken;
import Clinica.MSUsuario.model.modelUsuario;
import Clinica.MSUsuario.service.IAuthServiceUsu;

@RestController
@RequestMapping("/api/usuario")
public class AuthControllerUsu {

    
    private static final Logger logger = LoggerFactory.getLogger(AuthControllerUsu.class);
    @Autowired
    IAuthServiceUsu authServicesUsu;

    @Autowired
    UsuarioMapper usuarioMapper;

    @Autowired
    private JwtToken jwtTokenCroos;

    
    //busqueda general
    @GetMapping("/getAll")
        public ResponseEntity<ApiResponseUsu<List<AuthRequestUsu>>> getAll(){
            try{
                List<modelUsuario> usuarios = authServicesUsu.findAll();
                List<AuthRequestUsu> authRequests = usuarios.stream()
                .map(usuarioMapper::entityToDto)
                .collect(Collectors.toList());
                return ResponseEntity.ok(new ApiResponseUsu<>(HttpStatus.OK.value(),
                 "Consulta de listado de Usuario exitosa", authRequests));
            }catch(Exception ex){
                logger.error(MensajesParametrizados.MENSAJE_ERROR, ex);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponseUsu<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                 "Error al procesar la solicitud", null));
            }
        }
    
    //busqueda por ID
    @GetMapping("getById")
    public ResponseEntity<ApiResponseUsu<AuthRequestUsu>> getById(@RequestParam int id){
        try{
            modelUsuario usuarioModel = authServicesUsu.findById(id);
            if(usuarioModel !=null){
                AuthRequestUsu authRequest = usuarioMapper.entityToDto(usuarioModel);
                return ResponseEntity.ok(new ApiResponseUsu<>(HttpStatus.OK.value(),
                 "Consulta de Busqueda por ID exitosa", authRequest));
            }else{
                logger.info(MensajesParametrizados.MENSAJE_USUARIO_NO_ENCONTRADO_ID);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseUsu<>(HttpStatus.NOT_FOUND.value(),
                 "Usuario no encontrado", null));
            }
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponseUsu<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
             "Error al buscar usuario", null));
        }
    }

    //crear paciente
    @PostMapping("/create")
    public ResponseEntity<ApiResponseUsu<AuthRequestUsu>> create(@RequestBody AuthRequestUsu authRequestUsu) {
        try{
            modelUsuario usuarioModel = usuarioMapper.dtoTOEntity(authRequestUsu);
            usuarioModel = authServicesUsu.add(usuarioModel);
            AuthRequestUsu creaAuthRequest = usuarioMapper.entityToDto(usuarioModel);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponseUsu<>(HttpStatus.CREATED.value(), 
            MensajesParametrizados.MENSAJE_CREAR_USUARIO_EXITOSO, creaAuthRequest));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponseUsu<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            "Error al crear Usuario", null));
        }
    }

    //Modificar paciente
    @PutMapping("/update")
    public ResponseEntity<ApiResponseUsu<AuthRequestUsu>> update(@RequestBody AuthRequestUsu authRequestUsu) {
        try{
            modelUsuario usuarios = authServicesUsu.update(usuarioMapper.dtoTOEntity(authRequestUsu));
            AuthRequestUsu actualizarAuthRequest = usuarioMapper.entityToDto(usuarios);
            return ResponseEntity.ok(new ApiResponseUsu<>(HttpStatus.OK.value(),
             "Usuario actualizado", actualizarAuthRequest));
        }catch(UsuarioNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponseUsu<>(HttpStatus.NOT_FOUND.value(), 
            ex.getMessage(), null));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponseUsu<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error al actualizar paciente", null));
        }
    }
    

    //Eliminar paciente
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponseUsu<String>> delete(@PathVariable int id){

        try{
            boolean eliminar = authServicesUsu.delete(id);
            if(eliminar){
                logger.info(MensajesParametrizados.MENSAJE_ELIMINAR_USUARIO_EXITOSO);
                return ResponseEntity.ok(new ApiResponseUsu<>(HttpStatus.OK.value(),
                "Usuario Eliminado correctamente", null));
            }else{
                logger.info(MensajesParametrizados.MENSAJE_USUARIO_NO_ENCONTRADO);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseUsu<>(HttpStatus.NOT_FOUND.value(), 
                "No se encontro al Usuario a eliminar", null));
            }
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponseUsu<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error al eliminar usuario", null));
        }
    }

//private String generarTokenAutenticacion(modelUsuario usuario) {
    // aqui generaremos el token con JWT
//return "asdfsjfhdskjhfkjdshfjdbsfkjbdskjfbdskjbfdsbfdsbfnbdsjfbdsjbfdsjbjdsbfjdsbfjbdsjbdsjbdfsj";
//}

    @PostMapping()
    public ResponseEntity<?> post(@RequestBody AuthRequestUsu requestUsu) throws Exception{
        try {
            if (!authServicesUsu.validarCredenciales(requestUsu.getUsuario(), requestUsu.getClave())) {
                logger.info(MensajesParametrizados.MENSAJE_ERROR_AUTENTICACION);
                return new ResponseEntity<String>("INVALID_CREDENTIALS", HttpStatus.UNAUTHORIZED);
            }
            logger.info(MensajesParametrizados.MENSAJE_AUTENTICACION_EXITOSA);
            String token = jwtTokenCroos.generateToken(requestUsu);
            AuthResponseUsu response = new AuthResponseUsu(token, requestUsu.getUsuario(), "1d");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Manejo de la excepción
           logger.error(MensajesParametrizados.MENSAJE_ERROR_INTERNO_SERVIDOR, e.getMessage());
            return new ResponseEntity<String>("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
