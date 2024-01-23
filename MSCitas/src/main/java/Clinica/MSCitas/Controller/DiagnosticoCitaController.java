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
import Clinica.MSCitas.Dto.DiagnosticoCitaResquets;
import Clinica.MSCitas.Exception.CitasApiResponse;
import Clinica.MSCitas.Exception.CitasNotFoundException;
import Clinica.MSCitas.Mapper.CitasMapper;
import Clinica.MSCitas.Mapper.DiagnosticoMapper;
import Clinica.MSCitas.Message.CitasMessagePublish;
import Clinica.MSCitas.Model.ModelCitas;
import Clinica.MSCitas.Model.ModelDiagnosticoCita;
import Clinica.MSCitas.Service.ICitasService;
import Clinica.MSCitas.Service.IDiagnosticoCita;

@RestController
@RequestMapping("/api/DiagnosticoCita")
public class DiagnosticoCitaController {
    

    @Autowired
    IDiagnosticoCita diagnosticoService;

    @Autowired
    DiagnosticoMapper diagnosticoMapper;
   
    @Autowired
    CitasMessagePublish messagePublish;
//CitasMapper.dtoTOEntity(citasRequest);
    @PostMapping("/create")
    public ResponseEntity<CitasApiResponse<DiagnosticoCitaResquets>> add(@RequestBody DiagnosticoCitaResquets diagnosticoCitaResquets) {
        try{
            ModelDiagnosticoCita diagnosticoCita = diagnosticoMapper.dtoTOEntity(diagnosticoCitaResquets);
            diagnosticoCita = diagnosticoService.crearDiagnosticocita(diagnosticoCita);
            DiagnosticoCitaResquets creaDiagRequest = diagnosticoMapper.entityToDto(diagnosticoCita);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new CitasApiResponse<>(HttpStatus.CREATED.value(), 
            "diagnosticoCita creado", creaDiagRequest));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new CitasApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            "Error al crear diagnosticoCita", null));
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<CitasApiResponse<List<DiagnosticoCitaResquets>>> listarDiagnosticoCita(){

        try{
            List<ModelDiagnosticoCita> diagnosticoCita = diagnosticoService.listarDiagnosticocita();
            List<DiagnosticoCitaResquets> diagnosticoRequests = diagnosticoCita.stream()
            .map(diagnosticoMapper::entityToDto)
            .collect(Collectors.toList());
            return ResponseEntity.ok(new CitasApiResponse<>(HttpStatus.OK.value(),
            "Consulta de listado de diagnosticoCita Exitosa", diagnosticoRequests));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new CitasApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                 "Error al procesar la solicitud", null));
        }
    }

    @GetMapping("/getById")
    public ResponseEntity<CitasApiResponse<DiagnosticoCitaResquets>> getById(@RequestParam int id){
        //sfinal String mensaje1 = "eroor al buscar paciente";
        try{
            
            ModelDiagnosticoCita modelDiagnosticoCita = diagnosticoService.buscarIdDiagnosticocita(id);
            if(modelDiagnosticoCita !=null){
                DiagnosticoCitaResquets citasRequest = diagnosticoMapper.entityToDto(modelDiagnosticoCita);
                return ResponseEntity.ok(new CitasApiResponse<>(HttpStatus.OK.value(),
                 "Consulta de Busqueda por ID exitosa", citasRequest));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CitasApiResponse<>(HttpStatus.NOT_FOUND.value(),
                 "diagnosticoCita no encontrado", null));
            }
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new CitasApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "error al buscar diagnosticoCita", null));
        }
    }

    @PutMapping("/update/detalle")
    public ResponseEntity<CitasApiResponse<DiagnosticoCitaResquets>> update(@RequestBody DiagnosticoCitaResquets diagnosticoCitaResquets) {
        try{
            ModelDiagnosticoCita diagnosticoCita = diagnosticoService.actualizarDiagnosticocita(diagnosticoMapper.dtoTOEntity(diagnosticoCitaResquets));
            DiagnosticoCitaResquets actualizarDiagnisticoCItaRequest = diagnosticoMapper.entityToDto(diagnosticoCita);
            return ResponseEntity.ok(new CitasApiResponse<>(HttpStatus.OK.value(),
             "diagnosticoCita actualizado", actualizarDiagnisticoCItaRequest));
        }catch(CitasNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new CitasApiResponse<>(HttpStatus.NOT_FOUND.value(), 
            ex.getMessage(), null));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new CitasApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error al actualizar diagnosticoCita", null));
        }
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<CitasApiResponse<String>> delete(@PathVariable int id){

        try{
            boolean eliminar = diagnosticoService.deleteDiagnosticocita(id);
            if(eliminar){
                return ResponseEntity.ok(new CitasApiResponse<>(HttpStatus.OK.value(),
                "diagnosticoCita Eliminado correctamente", null));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CitasApiResponse<>(HttpStatus.NOT_FOUND.value(), 
                "No se encontro al diagnosticoCita a eliminar", null));
            }
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new CitasApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error al eliminar diagnosticoCita", null));
        }
    }

 @PutMapping("/actulizarDiag")
 public ResponseEntity<?> actualizarDiagnostico(@RequestBody DiagnosticoCitaResquets resquets) throws Exception{
     ModelDiagnosticoCita model = new ModelDiagnosticoCita();
     model.setDiagnosticoid(resquets.getDiagnosticoid());
     model.setDiagnostico(resquets.getDiagnostico());
     model = diagnosticoService.updatekafka(model);
     messagePublish.sendUpdateDiagnosticoEvent(model);
     return ResponseEntity.status(HttpStatus.CREATED).body(model);
 }
}
