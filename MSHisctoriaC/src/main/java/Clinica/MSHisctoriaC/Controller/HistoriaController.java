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
import Clinica.MSHisctoriaC.Mapper.HistoriaMapper;
import Clinica.MSHisctoriaC.dto.HistoriaRequest;
import Clinica.MSHisctoriaC.model.modelHistoria;
import Clinica.MSHisctoriaC.service.IHistoriaService;

@RestController
@RequestMapping("/api/historia")
public class HistoriaController {
    
    @Autowired
    IHistoriaService historiaService;

    @Autowired
    HistoriaMapper historiaMapper;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponseH<List<HistoriaRequest>>> getAll(){
        try{
            List<modelHistoria> historias = historiaService.findAll();
            List<HistoriaRequest> historiaRequests = historias.stream()
            .map(historiaMapper::entityToDto)
            .collect(Collectors.toList());
            return ResponseEntity.ok(new ApiResponseH<>(HttpStatus.OK.value(),
            "Consulta de listado de historia Exitosa", historiaRequests));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponseH<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                 "Error al procesar la solicitud", null));
        }
    }

     @GetMapping("getById")
    public ResponseEntity<ApiResponseH<HistoriaRequest>> getById(@RequestParam int id){
        //sfinal String mensaje1 = "eroor al buscar paciente";
        try{
            
            modelHistoria modelHistoria = historiaService.findById(id);
            if(modelHistoria !=null){
                HistoriaRequest historiaRequest = historiaMapper.entityToDto(modelHistoria);
                return ResponseEntity.ok(new ApiResponseH<>(HttpStatus.OK.value(),
                 "Consulta de Busqueda por ID exitosa", historiaRequest));
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
    public ResponseEntity<ApiResponseH<HistoriaRequest>> create(@RequestBody HistoriaRequest historiaRequest) {
        try{
            modelHistoria modelHistoria = historiaMapper.dtoTOEntity(historiaRequest);
            modelHistoria = historiaService.add(modelHistoria);
            HistoriaRequest creaHistoriaRequest = historiaMapper.entityToDto(modelHistoria);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponseH<>(HttpStatus.CREATED.value(), 
            "Historia creado", creaHistoriaRequest));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponseH<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            "Error al crear Historia", null));
        }
    }

    //Modificar paciente
    @PutMapping("/update")
    public ResponseEntity<ApiResponseH<HistoriaRequest>> update(@RequestBody HistoriaRequest historiaRequest) {
        try{
            modelHistoria historia = historiaService.update(historiaMapper.dtoTOEntity(historiaRequest));
            HistoriaRequest actualizarHistoriaRequest = historiaMapper.entityToDto(historia);
            return ResponseEntity.ok(new ApiResponseH<>(HttpStatus.OK.value(),
             "Paciente actualizado", actualizarHistoriaRequest));
        }catch(HistoriaNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponseH<>(HttpStatus.NOT_FOUND.value(), 
            ex.getMessage(), null));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponseH<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error al actualizar historia", null));
        }
    }

}
