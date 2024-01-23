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

import Clinica.MSLaboratorio.Dto.AnalisisResquest;
import Clinica.MSLaboratorio.Exception.LaboratorioApiResponse;
import Clinica.MSLaboratorio.Exception.LaboratorioNotFoundException;
import Clinica.MSLaboratorio.Mapper.AnalisisMapper;
import Clinica.MSLaboratorio.Model.ModelAnalisis;
import Clinica.MSLaboratorio.Service.IAnalisisService;

@RestController
@RequestMapping("/api/Analisis")
public class AnalisiController {
    

    @Autowired
    IAnalisisService analisisService;

    @Autowired
    AnalisisMapper analisisMapper;
//CitasMapper.dtoTOEntity(citasRequest);
    @PostMapping("/create")
    public ResponseEntity<LaboratorioApiResponse<AnalisisResquest>> add(@RequestBody AnalisisResquest analisisResquest) {
        try{
            ModelAnalisis analisis = analisisMapper.dtoTOEntity(analisisResquest);
            analisis = analisisService.crearAnalisis(analisis);
            AnalisisResquest creaHCitaRequest = analisisMapper.entityToDto(analisis);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new LaboratorioApiResponse<>(HttpStatus.CREATED.value(), 
            "Citas creado", creaHCitaRequest));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new LaboratorioApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            "Error al crear Analisis", null));
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<LaboratorioApiResponse<List<AnalisisResquest>>> listarCita(){

        try{
            List<ModelAnalisis> analisis = analisisService.ListarAnalisis();
            List<AnalisisResquest> analisisResquests = analisis.stream()
            .map(analisisMapper::entityToDto)
            .collect(Collectors.toList());
            return ResponseEntity.ok(new LaboratorioApiResponse<>(HttpStatus.OK.value(),
            "Consulta de listado de Analisis Exitosa", analisisResquests));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new LaboratorioApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                 "Error al procesar la solicitud", null));
        }
    }

    @GetMapping("/getById")
    public ResponseEntity<LaboratorioApiResponse<AnalisisResquest>> getById(@RequestParam int id){
        //sfinal String mensaje1 = "eroor al buscar paciente";
        try{
            
            ModelAnalisis modelAnalisis = analisisService.buscarIdAnalisis(id);
            if(modelAnalisis !=null){
                AnalisisResquest citasRequest = analisisMapper.entityToDto(modelAnalisis);
                return ResponseEntity.ok(new LaboratorioApiResponse<>(HttpStatus.OK.value(),
                 "Consulta de Busqueda por ID exitosa", citasRequest));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new LaboratorioApiResponse<>(HttpStatus.NOT_FOUND.value(),
                 "Citas no encontrado", null));
            }
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new LaboratorioApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "error al buscar Analisis", null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<LaboratorioApiResponse<AnalisisResquest>> update(@RequestBody AnalisisResquest analisisResquest) {
        try{
            ModelAnalisis modelAnalisis = analisisService.actualizarAnalisis(analisisMapper.dtoTOEntity(analisisResquest));
            AnalisisResquest actualizarDetalleRequest = analisisMapper.entityToDto(modelAnalisis);
            return ResponseEntity.ok(new LaboratorioApiResponse<>(HttpStatus.OK.value(),
             "Analisis actualizado", actualizarDetalleRequest));
        }catch(LaboratorioNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new LaboratorioApiResponse<>(HttpStatus.NOT_FOUND.value(), 
            ex.getMessage(), null));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new LaboratorioApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error al actualizar Analisis", null));
        }
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<LaboratorioApiResponse<String>> delete(@PathVariable int id){

        try{
            boolean eliminar = analisisService.deleteAnalisis(id);
            if(eliminar){
                return ResponseEntity.ok(new LaboratorioApiResponse<>(HttpStatus.OK.value(),
                "Paciente Eliminado correctamente", null));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new LaboratorioApiResponse<>(HttpStatus.NOT_FOUND.value(), 
                "No se encontro al Citas a eliminar", null));
            }
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new LaboratorioApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error al eliminar Citas", null));
        }
    }
}
