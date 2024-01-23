package Clinica.MSPaciente.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Clinica.MSPaciente.Exeption.ApiResponse;
import Clinica.MSPaciente.Exeption.PacienteNotFoundException;
import Clinica.MSPaciente.Mapper.PacienteMapper;
import Clinica.MSPaciente.dto.AuthRequest;
import Clinica.MSPaciente.message.MensajesParametrizados;
import Clinica.MSPaciente.model.modelPaciente;
import Clinica.MSPaciente.services.IAuthServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/paciente")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    IAuthServices authServices;

    @Autowired
    PacienteMapper pacienteMapper;

    //busqueda general
    @GetMapping("/getAll")
        public ResponseEntity<ApiResponse<List<AuthRequest>>> getAll(){
            try{
                logger.info(MensajesParametrizados.MENSAJE_PACIENTE_LISTADO);
                List<modelPaciente> pacientes = authServices.findAll();
                List<AuthRequest> authRequests = pacientes.stream()
                .map(pacienteMapper::entityToDto)
                .collect(Collectors.toList());
                return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                 MensajesParametrizados.MENSAJE_PACIENTE_LISTADO, authRequests));
            }catch(Exception ex){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                 MensajesParametrizados.MENSAJE_ERROR, null));
            }
        }
    
    //busqueda por ID
    @GetMapping("/getById")
    public ResponseEntity<ApiResponse<AuthRequest>> getById(@RequestParam int id){
        //sfinal String mensaje1 = "eroor al buscar paciente";
        try{
            modelPaciente modelPaciente = authServices.findById(id);
            if(modelPaciente !=null){
                logger.info(MensajesParametrizados.MENSAJE_PACIENTE_LISTADOID);
                AuthRequest authRequest = pacienteMapper.entityToDto(modelPaciente);
                return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                MensajesParametrizados.MENSAJE_PACIENTE_LISTADOID, authRequest));
            }else{
                logger.info(MensajesParametrizados.MENSAJE_PACIENTE_LISTADOID);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(),
                MensajesParametrizados.MENSAJE_PACIENTE_NO_ENCONTRADO_ID, null));
            }
        }catch(Exception ex){
            logger.error(MensajesParametrizados.MENSAJE_ERROR_INTERNO_SERVIDOR, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            MensajesParametrizados.MENSAJE_ERROR_INTERNO_SERVIDOR, null));
        }
    }

    //crear paciente
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<AuthRequest>> create(@RequestBody AuthRequest authRequest) {
        try{
            logger.info(MensajesParametrizados.MENSAJE_CREAR_PACIENTE_EXITOSO);
            modelPaciente pacienteModel = pacienteMapper.dtoTOEntity(authRequest);
            pacienteModel = authServices.add(pacienteModel);
            AuthRequest creaAuthRequest = pacienteMapper.entityToDto(pacienteModel);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(HttpStatus.CREATED.value(), 
            MensajesParametrizados.MENSAJE_CREAR_PACIENTE_EXITOSO, creaAuthRequest));
        }catch(Exception ex){
            logger.error(MensajesParametrizados.MENSAJE_CREAR_PACIENTE_NOEXITOSO, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            MensajesParametrizados.MENSAJE_ERROR_INTERNO_SERVIDOR, null));
        }
    }

    //Modificar paciente
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<AuthRequest>> update(@RequestBody AuthRequest authRequest) {
        try{
            logger.info(MensajesParametrizados.MENSAJE_PACIENTE_EDITADO_EXITOSO);
            modelPaciente paciente = authServices.update(pacienteMapper.dtoTOEntity(authRequest));
            AuthRequest actualizarAuthRequest = pacienteMapper.entityToDto(paciente);
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
            MensajesParametrizados.MENSAJE_PACIENTE_EDITADO_EXITOSO, actualizarAuthRequest));
        }catch(PacienteNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), 
            ex.getMessage(), null));
        }catch(Exception ex){
            logger.error(MensajesParametrizados.MENSAJE_ERROR_INTERNO_SERVIDOR, ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            MensajesParametrizados.MENSAJE_ERROR_INTERNO_SERVIDOR, null));
        }
    }
    

    //Eliminar paciente
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable int id){

        try{
            
            boolean eliminar = authServices.delete(id);
            if(eliminar){
                logger.info(MensajesParametrizados.MENSAJE_ELIMINAR_PACIENTE_EXITOSO);
                return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                MensajesParametrizados.MENSAJE_ELIMINAR_PACIENTE_EXITOSO, null));
            }else{
                logger.info(MensajesParametrizados.MENSAJE_PACIENTE_NO_ENCONTRADOELIMINAR);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), 
                MensajesParametrizados.MENSAJE_PACIENTE_NO_ENCONTRADOELIMINAR, null));
            }
        }catch(Exception ex){
            logger.error(MensajesParametrizados.MENSAJE_ERROR_INTERNO_SERVIDOR, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            MensajesParametrizados.MENSAJE_ERROR_INTERNO_SERVIDOR, null));
        }
    }

    @GetMapping("/buscarRedis")
    public List<modelPaciente> findAllRedis() {
       return (List<modelPaciente>) authServices.findAll();
    }
    
}
