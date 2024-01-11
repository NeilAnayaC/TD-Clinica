package Clinica.MSPaciente.Controller;

import java.util.List;
import java.util.stream.Collectors;

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
import Clinica.MSPaciente.model.modelPaciente;
import Clinica.MSPaciente.services.IAuthServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/paciente")
public class AuthController {
    @Autowired
    IAuthServices authServices;

    @Autowired
    PacienteMapper pacienteMapper;

    //busqueda general
    @GetMapping("/getAll")
        public ResponseEntity<ApiResponse<List<AuthRequest>>> getAll(){
            try{
                List<modelPaciente> pacientes = authServices.findAll();
                List<AuthRequest> authRequests = pacientes.stream()
                .map(pacienteMapper::entityToDto)
                .collect(Collectors.toList());
                return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                 "Consulta de listado exitosa", authRequests));
            }catch(Exception ex){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                 "Error al procesar la solicitud", null));
            }
        }
    
    //busqueda por ID
    @GetMapping("getById")
    public ResponseEntity<ApiResponse<AuthRequest>> getById(@RequestParam int id){
        try{
            modelPaciente modelPaciente = authServices.findById(id);
            if(modelPaciente !=null){
                AuthRequest authRequest = pacienteMapper.entityToDto(modelPaciente);
                return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                 "Consulta de Busqueda por ID exitosa", authRequest));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(),
                 "Paciente no encontrado", null));
            }
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
             "Error al buscar paciente", null));
        }
    }

    //crear paciente
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<AuthRequest>> create(@RequestBody AuthRequest authRequest) {
        try{
            modelPaciente pacienteModel = pacienteMapper.dtoTOEntity(authRequest);
            pacienteModel = authServices.add(pacienteModel);
            AuthRequest creaAuthRequest = pacienteMapper.entityToDto(pacienteModel);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(HttpStatus.CREATED.value(), 
            "Paciente creado", creaAuthRequest));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            "Error al crear paciente", null));
        }
    }

    //Modificar paciente
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<AuthRequest>> update(@RequestBody AuthRequest authRequest) {
        try{
            modelPaciente paciente = authServices.update(pacienteMapper.dtoTOEntity(authRequest));
            AuthRequest actualizarAuthRequest = pacienteMapper.entityToDto(paciente);
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
             "Paciente actualizado", actualizarAuthRequest));
        }catch(PacienteNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), 
            ex.getMessage(), null));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error al actualizar paciente", null));
        }
    }
    

    //Eliminar paciente
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable int id){

        try{
            boolean eliminar = authServices.delete(id);
            if(eliminar){
                return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                "Paciente Eliminado correctamente", null));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), 
                "No se encontro al paciente a eliminar", null));
            }
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error al eliminar paciente", null));
        }
    }
}
