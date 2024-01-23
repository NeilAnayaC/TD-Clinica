package Clinica.MSHisctoriaC.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import Clinica.MSHisctoriaC.message.MensajeParametrizadosDetalle;
import Clinica.MSHisctoriaC.message.MensajesParametrizados;
import Clinica.MSHisctoriaC.model.modelDetalle;
import Clinica.MSHisctoriaC.service.IDetalleService;

@RestController
@RequestMapping("/api/detalle")
public class DetalleController {
    
    private static final Logger logger = LoggerFactory.getLogger(DetalleController.class);

    @Autowired
    IDetalleService detalleService;

    @Autowired
    DetalleMapper detalleMapper;

    
    @GetMapping("/getAll")
        public ResponseEntity<ApiResponseH<List<DetalleRequest>>> getAll(){
            try{
                logger.info(MensajeParametrizadosDetalle.MENSAJE_DETALLE_LISTADO);
                List<modelDetalle> detalles = detalleService.findAll();
                List<DetalleRequest> detalleRequests = detalles.stream()
                .map(detalleMapper::entityToDto)
                .collect(Collectors.toList());
                return ResponseEntity.ok(new ApiResponseH<>(HttpStatus.OK.value(),
                MensajeParametrizadosDetalle.MENSAJE_DETALLE_LISTADO, detalleRequests));
            }catch(Exception ex){
                logger.error(MensajeParametrizadosDetalle.MENSAJE_ERROR_INTERNO_SERVIDOR_DETALLE, ex);
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
                logger.info(MensajeParametrizadosDetalle.MENSAJE_DETALLE_LISTADOID);
                DetalleRequest detalleRequest = detalleMapper.entityToDto(modelDetalle);
                return ResponseEntity.ok(new ApiResponseH<>(HttpStatus.OK.value(),
                MensajeParametrizadosDetalle.MENSAJE_DETALLE_LISTADOID, detalleRequest));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseH<>(HttpStatus.NOT_FOUND.value(),
                 MensajeParametrizadosDetalle.MENSAJE_DETALLE_NO_ENCONTRADO_ID, null));
            }
        }catch(Exception ex){
            logger.error(MensajeParametrizadosDetalle.MENSAJE_ERROR_INTERNO_SERVIDOR_DETALLE, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponseH<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            MensajeParametrizadosDetalle.MENSAJE_ERROR_INTERNO_SERVIDOR_DETALLE, null));
        }
    }

    //crear paciente
    @PostMapping("/create")
    public ResponseEntity<ApiResponseH<DetalleRequest>> create(@RequestBody DetalleRequest detalleRequest) {
        try{

            logger.info(MensajeParametrizadosDetalle.MENSAJE_CREAR_DETALLE_EXITOSO);
            modelDetalle detalleModel = detalleMapper.dtoTOEntity(detalleRequest);
            detalleModel = detalleService.add(detalleModel);
            DetalleRequest creaDetalleRequest = detalleMapper.entityToDto(detalleModel);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponseH<>(HttpStatus.CREATED.value(), 
            "Paciente creado", creaDetalleRequest));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponseH<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            MensajeParametrizadosDetalle.MENSAJE_CREAR_DETALLE_EXITOSO, null));
        }
    }

    //Modificar paciente
    @PutMapping("/update")
    public ResponseEntity<ApiResponseH<DetalleRequest>> update(@RequestBody DetalleRequest detalleRequest) {
        try{
            logger.info(MensajeParametrizadosDetalle.MENSAJE_DETALLE_EDITADO_EXITOSO);
            modelDetalle detalle = detalleService.update(detalleMapper.dtoTOEntity(detalleRequest));
            DetalleRequest actualizarDetalleRequest = detalleMapper.entityToDto(detalle);
            return ResponseEntity.ok(new ApiResponseH<>(HttpStatus.OK.value(),
            MensajeParametrizadosDetalle.MENSAJE_DETALLE_EDITADO_EXITOSO, actualizarDetalleRequest));
        }catch(HistoriaNotFoundException ex){
            logger.info(MensajeParametrizadosDetalle.MENSAJE_DETALLE_NO_ENCONTRADO);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponseH<>(HttpStatus.NOT_FOUND.value(), 
            ex.getMessage(), null));
        }catch(Exception ex){
            logger.error(MensajeParametrizadosDetalle.MENSAJE_ERROR_INTERNO_SERVIDOR_DETALLE, ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponseH<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            MensajeParametrizadosDetalle.MENSAJE_ERROR_INTERNO_SERVIDOR_DETALLE, null));
        }
    }
}
