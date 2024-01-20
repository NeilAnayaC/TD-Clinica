 package Clinica.MSDoctores.Mapper;

 import org.springframework.stereotype.Component;

 import Clinica.MSDoctores.Dto.DoctorResquest;
 import Clinica.MSDoctores.Model.DoctorModel;

 @Component
 public class DoctorMapper {
     public DoctorResquest entityToDto(DoctorModel doctor){
         DoctorResquest dto = new DoctorResquest();
         dto.setNombred(doctor.getNombred());
         dto.setApellidod(doctor.getApellidod());
         dto.setEspecialidadd(doctor.getEspecialidadd());
         dto.setCelular(doctor.getCelular());
         dto.setCorreo(doctor.getCorreo());
         return dto;
     }

     public DoctorModel dtoTOEntity(DoctorResquest dto){
         DoctorModel doctor = new DoctorModel();
         doctor.setNombred(dto.getNombred());
         doctor.setApellidod(dto.getApellidod());
         doctor.setEspecialidadd(dto.getEspecialidadd());
         doctor.setCelular(dto.getCelular());
         doctor.setCorreo(dto.getCorreo());
         return doctor;
     }
 }
