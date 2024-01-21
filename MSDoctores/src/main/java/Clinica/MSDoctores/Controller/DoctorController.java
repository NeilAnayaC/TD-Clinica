package Clinica.MSDoctores.Controller;

import java.util.List;

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

import Clinica.MSDoctores.Dto.DoctorResquest;
//import Clinica.MSDoctores.Exception.DoctorApiResponse;
import Clinica.MSDoctores.Mapper.DoctorMapper;
import Clinica.MSDoctores.Model.DoctorModel;
import Clinica.MSDoctores.Service.IDoctorService;

@RestController
@RequestMapping("/api/doctores")
public class DoctorController {
    

    @Autowired
    IDoctorService doctorService;

     @Autowired
     DoctorMapper doctorMapper;

     @PostMapping("/createdoctor")
     public ResponseEntity<DoctorModel> add (@RequestBody DoctorModel doctor){
         try {
             DoctorModel nuevoDoctor = doctorService.add(doctor);
             return new ResponseEntity<>(nuevoDoctor, HttpStatus.CREATED);
         } catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }

     @GetMapping("/getByIddoctor")
     public ResponseEntity<DoctorModel> getById(@RequestParam String id){
         try {
             DoctorModel doctor = doctorService.findById(id);

             if (doctor != null) {
                 return new ResponseEntity<>(doctor, HttpStatus.OK);
             } else {
                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             }
         } catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }

     @GetMapping("/getAlldoctor")
     public ResponseEntity<List<DoctorModel>> getAll(){
         try {
             List<DoctorModel> doctores = doctorService.findAll();
             return new ResponseEntity<>(doctores, HttpStatus.OK);
         } catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }

     @PutMapping("/updatedoctor")
     public ResponseEntity<DoctorModel> update(@RequestBody DoctorResquest doctorRequest){
         try {
             DoctorModel doctorActual = doctorService.findById(doctorRequest.getId());
    
             if (doctorActual != null) {
                 // Actualizar los campos del doctor actual con los valores proporcionados en doctorRequest
                 doctorActual.setNombred(doctorRequest.getNombred());
                 doctorActual.setApellidod(doctorRequest.getApellidod());
                 doctorActual.setEspecialidadd(doctorRequest.getEspecialidadd());
                 doctorActual.setCelular(doctorRequest.getCelular());
                 doctorActual.setCorreo(doctorRequest.getCorreo());
    
                 // Llamar al servicio para realizar la actualización en la base de datos
                 DoctorModel doctorActualizado = doctorService.update(doctorActual);
    
                 if (doctorActualizado != null) {
                     return new ResponseEntity<>(doctorActualizado, HttpStatus.OK);
                 } else {
                     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                 }
             } else {
                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             }
         } catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }

     @DeleteMapping("/deletedoctor")
     public ResponseEntity<String> deleteDoctor(@PathVariable String id) {
         try {
             boolean eliminado = doctorService.delete(id);

             if (eliminado) {
                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
             } else {
                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             }
         } catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }

}
