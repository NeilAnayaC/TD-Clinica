package Clinica.MSCitas.Controller;

import java.util.List;
import java.util.stream.Collectors;

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

import Clinica.MSCitas.Dto.CitasRequest;
import Clinica.MSCitas.Exception.CitasApiResponse;
import Clinica.MSCitas.Exception.CitasNotFoundException;
import Clinica.MSCitas.Mapper.CitasMapper;
import Clinica.MSCitas.Model.ModelCitas;
import Clinica.MSCitas.Service.ICitasService;

@RestController
@RequestMapping("/api/Cita")
public class CitasController {
    

    @Autowired
    ICitasService citasService;

    @Autowired
    CitasMapper citasMapper;
//CitasMapper.dtoTOEntity(citasRequest);
    @PostMapping("/create")
    public ResponseEntity<CitasApiResponse<CitasRequest>> add(@RequestBody CitasRequest citasRequest) {
        try{
            ModelCitas citas = citasMapper.dtoTOEntity(citasRequest);
            citas = citasService.crearcita(citas);
            CitasRequest creaHCitaRequest = citasMapper.entityToDto(citas);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new CitasApiResponse<>(HttpStatus.CREATED.value(), 
            "Citas creado", creaHCitaRequest));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new CitasApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            "Error al crear Historia", null));
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<CitasApiResponse<List<CitasRequest>>> listarCita(){

        try{
            List<ModelCitas> citas = citasService.listarCita();
            List<CitasRequest> historiaRequests = citas.stream()
            .map(citasMapper::entityToDto)
            .collect(Collectors.toList());
            return ResponseEntity.ok(new CitasApiResponse<>(HttpStatus.OK.value(),
            "Consulta de listado de historia Exitosa", historiaRequests));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new CitasApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                 "Error al procesar la solicitud", null));
        }
    }

    @GetMapping("/getById")
    public ResponseEntity<CitasApiResponse<CitasRequest>> getById(@RequestParam int id){
        //sfinal String mensaje1 = "eroor al buscar paciente";
        try{
            
            ModelCitas modelCitas = citasService.buscarIdCita(id);
            if(modelCitas !=null){
                CitasRequest citasRequest = citasMapper.entityToDto(modelCitas);
                return ResponseEntity.ok(new CitasApiResponse<>(HttpStatus.OK.value(),
                 "Consulta de Busqueda por ID exitosa", citasRequest));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CitasApiResponse<>(HttpStatus.NOT_FOUND.value(),
                 "Citas no encontrado", null));
            }
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new CitasApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "error al buscar Citas", null));
        }
    }

    @PutMapping("/update/detalle")
    public ResponseEntity<CitasApiResponse<CitasRequest>> update(@RequestBody CitasRequest citasRequest) {
        try{
            ModelCitas detalle = citasService.actualizarCita(citasMapper.dtoTOEntity(citasRequest));
            CitasRequest actualizarDetalleRequest = citasMapper.entityToDto(detalle);
            return ResponseEntity.ok(new CitasApiResponse<>(HttpStatus.OK.value(),
             "Paciente actualizado", actualizarDetalleRequest));
        }catch(CitasNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new CitasApiResponse<>(HttpStatus.NOT_FOUND.value(), 
            ex.getMessage(), null));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new CitasApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error al actualizar Citas", null));
        }
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<CitasApiResponse<String>> delete(@PathVariable int id){

        try{
            boolean eliminar = citasService.deleteCita(id);
            if(eliminar){
                return ResponseEntity.ok(new CitasApiResponse<>(HttpStatus.OK.value(),
                "Paciente Eliminado correctamente", null));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CitasApiResponse<>(HttpStatus.NOT_FOUND.value(), 
                "No se encontro al Citas a eliminar", null));
            }
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new CitasApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error al eliminar Citas", null));
        }
    }
}
