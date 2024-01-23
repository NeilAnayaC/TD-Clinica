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
import Clinica.MSHisctoriaC.Mapper.HistoriaMapper;
import Clinica.MSHisctoriaC.dto.HistoriaRequest;
import Clinica.MSHisctoriaC.message.MensajesParametrizados;
import Clinica.MSHisctoriaC.model.modelHistoria;
import Clinica.MSHisctoriaC.service.IHistoriaService;

@RestController
@RequestMapping("/api/historia")
public class HistoriaController {
    
    private static final Logger logger = LoggerFactory.getLogger(HistoriaController.class);

    @Autowired
    IHistoriaService historiaService;

    @Autowired
    HistoriaMapper historiaMapper;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponseH<List<HistoriaRequest>>> getAll(){
        try{
            logger.info(MensajesParametrizados.MENSAJE_HISTORIA_LISTADO);
            List<modelHistoria> historias = historiaService.findAll();
            List<HistoriaRequest> historiaRequests = historias.stream()
            .map(historiaMapper::entityToDto)
            .collect(Collectors.toList());
            return ResponseEntity.ok(new ApiResponseH<>(HttpStatus.OK.value(),
            MensajesParametrizados.MENSAJE_HISTORIA_LISTADO, historiaRequests));
        }catch(Exception ex){
            logger.error(MensajesParametrizados.MENSAJE_ERROR_INTERNO_SERVIDOR, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponseH<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                MensajesParametrizados.MENSAJE_ERROR_INTERNO_SERVIDOR, null));
        }
    }

     @GetMapping("getById")
    public ResponseEntity<ApiResponseH<HistoriaRequest>> getById(@RequestParam int id){
        //sfinal String mensaje1 = "eroor al buscar paciente";
        try{
            
            modelHistoria modelHistoria = historiaService.findById(id);
            if(modelHistoria !=null){
                logger.info(MensajesParametrizados.MENSAJE_HISTORIA_LISTADOID);
                HistoriaRequest historiaRequest = historiaMapper.entityToDto(modelHistoria);
                return ResponseEntity.ok(new ApiResponseH<>(HttpStatus.OK.value(),
                MensajesParametrizados.MENSAJE_HISTORIA_LISTADOID, historiaRequest));
            }else{
                logger.info(MensajesParametrizados.MENSAJE_HISTORIA_NO_ENCONTRADO);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseH<>(HttpStatus.NOT_FOUND.value(),
                MensajesParametrizados.MENSAJE_HISTORIA_NO_ENCONTRADO, null));
            }
        }catch(Exception ex){
            logger.error(MensajesParametrizados.MENSAJE_ERROR_INTERNO_SERVIDOR, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponseH<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            MensajesParametrizados.MENSAJE_ERROR_INTERNO_SERVIDOR, null));
        }
    }

    //crear paciente
    @PostMapping("/create")
    public ResponseEntity<ApiResponseH<HistoriaRequest>> create(@RequestBody HistoriaRequest historiaRequest) {
        try{
            logger.info(MensajesParametrizados.MENSAJE_CREAR_HISTORIA_EXITOSO);
            modelHistoria modelHistoria = historiaMapper.dtoTOEntity(historiaRequest);
            modelHistoria = historiaService.add(modelHistoria);
            HistoriaRequest creaHistoriaRequest = historiaMapper.entityToDto(modelHistoria);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponseH<>(HttpStatus.CREATED.value(), 
            MensajesParametrizados.MENSAJE_CREAR_HISTORIA_EXITOSO, creaHistoriaRequest));
        }catch(Exception ex){
            logger.error(MensajesParametrizados.MENSAJE_CREAR_HISTORIA_NOEXITOSO, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponseH<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            MensajesParametrizados.MENSAJE_CREAR_HISTORIA_NOEXITOSO, null));
        }
    }

    //Modificar paciente
    @PutMapping("/update")
    public ResponseEntity<ApiResponseH<HistoriaRequest>> update(@RequestBody HistoriaRequest historiaRequest) {
        try{
            logger.info(MensajesParametrizados.MENSAJE_HISTORIA_EDITADO_EXITOSO);
            modelHistoria historia = historiaService.update(historiaMapper.dtoTOEntity(historiaRequest));
            HistoriaRequest actualizarHistoriaRequest = historiaMapper.entityToDto(historia);
            return ResponseEntity.ok(new ApiResponseH<>(HttpStatus.OK.value(),
            MensajesParametrizados.MENSAJE_HISTORIA_EDITADO_EXITOSO, actualizarHistoriaRequest));
        }catch(HistoriaNotFoundException ex){
            logger.info(MensajesParametrizados.MENSAJE_HISTORIA_NO_ENCONTRADO);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponseH<>(HttpStatus.NOT_FOUND.value(), 
            ex.getMessage(), null));
        }catch(Exception ex){
            logger.error(MensajesParametrizados.MENSAJE_HISTORIA_NO_ENCONTRADO, ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponseH<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            MensajesParametrizados.MENSAJE_ERROR_INTERNO_SERVIDOR, null));
        }
    }

}
