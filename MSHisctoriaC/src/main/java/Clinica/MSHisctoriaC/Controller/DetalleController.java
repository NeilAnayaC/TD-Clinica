package Clinica.MSHisctoriaC.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Clinica.MSHisctoriaC.Exection.ApiResponseH;
import Clinica.MSHisctoriaC.Exection.HistoriaNotFoundException;
import Clinica.MSHisctoriaC.Mapper.DetalleMapper;
import Clinica.MSHisctoriaC.dto.DetalleRequest;
import Clinica.MSHisctoriaC.model.modelDetalle;
import Clinica.MSHisctoriaC.service.IDetalleService;

@RestController
@RequestMapping("/api/detalle")
public class DetalleController {
    
    @Autowired
    IDetalleService detalleService;

    @Autowired
    DetalleMapper detalleMapper;

    
    @GetMapping("/getAll")
        public ResponseEntity<ApiResponseH<List<DetalleRequest>>> getAll(){
            try{
                List<modelDetalle> detalles = detalleService.findAll();
                List<DetalleRequest> detalleRequests = detalles.stream()
                .map(detalleMapper::entityToDto)
                .collect(Collectors.toList());
                return ResponseEntity.ok(new ApiResponseH<>(HttpStatus.OK.value(),
                 "Consulta de listado exitosa", detalleRequests));
            }catch(Exception ex){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponseH<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                 "Error al procesar la solicitud", null));
            }
        }

        //busqueda por ID
    @GetMapping("/getById")
    public ResponseEntity<ApiResponseH<DetalleRequest>> getById(@RequestParam int id){
        //sfinal String mensaje1 = "eroor al buscar paciente";
        try{
            
            modelDetalle modelDetalle = detalleService.findById(id);
            if(modelDetalle !=null){
                DetalleRequest detalleRequest = detalleMapper.entityToDto(modelDetalle);
                return ResponseEntity.ok(new ApiResponseH<>(HttpStatus.OK.value(),
                 "Consulta de Busqueda por ID exitosa", detalleRequest));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseH<>(HttpStatus.NOT_FOUND.value(),
                 "Paciente no encontrado", null));
            }
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponseH<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "eroor al buscar paciente", null));
        }
    }

    //crear paciente
    @PostMapping("/create")
    public ResponseEntity<ApiResponseH<DetalleRequest>> create(@RequestBody DetalleRequest detalleRequest) {
        try{
            modelDetalle detalleModel = detalleMapper.dtoTOEntity(detalleRequest);
            detalleModel = detalleService.add(detalleModel);
            DetalleRequest creaDetalleRequest = detalleMapper.entityToDto(detalleModel);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponseH<>(HttpStatus.CREATED.value(), 
            "Paciente creado", creaDetalleRequest));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponseH<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            "Error al crear paciente", null));
        }
    }

    //Modificar paciente
    @PutMapping("/update")
    public ResponseEntity<ApiResponseH<DetalleRequest>> update(@RequestBody DetalleRequest detalleRequest) {
        try{
            modelDetalle detalle = detalleService.update(detalleMapper.dtoTOEntity(detalleRequest));
            DetalleRequest actualizarDetalleRequest = detalleMapper.entityToDto(detalle);
            return ResponseEntity.ok(new ApiResponseH<>(HttpStatus.OK.value(),
             "Paciente actualizado", actualizarDetalleRequest));
        }catch(HistoriaNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponseH<>(HttpStatus.NOT_FOUND.value(), 
            ex.getMessage(), null));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponseH<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error al actualizar paciente", null));
        }
    }
}
