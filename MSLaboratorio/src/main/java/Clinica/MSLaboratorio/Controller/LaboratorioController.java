package Clinica.MSLaboratorio.Controller;

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

import Clinica.MSLaboratorio.Dto.LaboratorioRequest;
import Clinica.MSLaboratorio.Exception.LaboratorioApiResponse;
import Clinica.MSLaboratorio.Exception.LaboratorioNotFoundException;
import Clinica.MSLaboratorio.Mapper.LaboratorioMapper;
import Clinica.MSLaboratorio.Model.ModelLaboratorio;
import Clinica.MSLaboratorio.Service.ILaboratorioService;

@RestController
@RequestMapping("/api/Resultados")
public class LaboratorioController {
    
    @Autowired
    ILaboratorioService laboratorioService;

    @Autowired
    LaboratorioMapper laboratorioMapper;
//CitasMapper.dtoTOEntity(citasRequest);
    @PostMapping("/create")
    public ResponseEntity<LaboratorioApiResponse<LaboratorioRequest>> add(@RequestBody LaboratorioRequest laboratorioRequest) {
        try{
            ModelLaboratorio resultadLaboratorio = laboratorioMapper.dtoTOEntity(laboratorioRequest);
            resultadLaboratorio = laboratorioService.crearLaboratorio(resultadLaboratorio);
            LaboratorioRequest creaHCitaRequest = laboratorioMapper.entityToDto(resultadLaboratorio);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new LaboratorioApiResponse<>(HttpStatus.CREATED.value(), 
            "Resultado analisis creado", creaHCitaRequest));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new LaboratorioApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            "Error al crear Resultado analisis", null));
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<LaboratorioApiResponse<List<LaboratorioRequest>>> listarCita(){

        try{
            List<ModelLaboratorio> resultadLaboratorio = laboratorioService.listarLaboratorios();
            List<LaboratorioRequest> historiaRequests = resultadLaboratorio.stream()
            .map(laboratorioMapper::entityToDto)
            .collect(Collectors.toList());
            return ResponseEntity.ok(new LaboratorioApiResponse<>(HttpStatus.OK.value(),
            "Consulta de listado de Resultado analisis Exitosa", historiaRequests));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new LaboratorioApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                 "Error al procesar la solicitud", null));
        }
    }

    @GetMapping("/getById")
    public ResponseEntity<LaboratorioApiResponse<LaboratorioRequest>> getById(@RequestParam int id){
        //sfinal String mensaje1 = "eroor al buscar paciente";
        try{
            
            ModelLaboratorio modelLaboratorio = laboratorioService.buscarIdLaboratorio(id);
            if(modelLaboratorio !=null){
                LaboratorioRequest citasRequest = laboratorioMapper.entityToDto(modelLaboratorio);
                return ResponseEntity.ok(new LaboratorioApiResponse<>(HttpStatus.OK.value(),
                 "Consulta de Busqueda por ID exitosa", citasRequest));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new LaboratorioApiResponse<>(HttpStatus.NOT_FOUND.value(),
                 "Reultado analisis no encontrado", null));
            }
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new LaboratorioApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "error al buscar Resultado analisis", null));
        }
    }

    @PutMapping("/update/detalle")
    public ResponseEntity<LaboratorioApiResponse<LaboratorioRequest>> update(@RequestBody LaboratorioRequest laboratorioRequest) {
        try{
            ModelLaboratorio modelLaboratorio = laboratorioService.actualizarLaboratorio(laboratorioMapper.dtoTOEntity(laboratorioRequest));
            LaboratorioRequest actualizarDetalleRequest = laboratorioMapper.entityToDto(modelLaboratorio);
            return ResponseEntity.ok(new LaboratorioApiResponse<>(HttpStatus.OK.value(),
             "Resultados analisis actualizado", actualizarDetalleRequest));
        }catch(LaboratorioNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new LaboratorioApiResponse<>(HttpStatus.NOT_FOUND.value(), 
            ex.getMessage(), null));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new LaboratorioApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error al actualizar Resultado analisis", null));
        }
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<LaboratorioApiResponse<String>> delete(@PathVariable int id){

        try{
            boolean eliminar = laboratorioService.deleteLaboratorios(id);
            if(eliminar){
                return ResponseEntity.ok(new LaboratorioApiResponse<>(HttpStatus.OK.value(),
                "Paciente Eliminado correctamente", null));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new LaboratorioApiResponse<>(HttpStatus.NOT_FOUND.value(), 
                "No se encontro al Resultado analisis a eliminar", null));
            }
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new LaboratorioApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error al eliminar Resultado analisis", null));
        }
    }
}
