 package Clinica.MSDoctores.Service;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import Clinica.MSDoctores.Model.DoctorModel;
 import Clinica.MSDoctores.Repository.IDoctorRespository;

 @Service
 public class DoctorService implements IDoctorService{
    
     @Autowired
     IDoctorRespository doctorRespository;

     @Override
     public DoctorModel add(DoctorModel doctor){
         return doctorRespository.save(doctor);
     }

     @Override
     public DoctorModel findById(String id){
         return doctorRespository.findById(id).orElse(null);
     }

     @Override
     public Iterable<DoctorModel> findAll(){
         return doctorRespository.findAll();
     }

     @Override
     public DoctorModel update(DoctorModel doctor){
         return doctorRespository.save(doctor);
     }

     @Override
     public boolean delete(String id){
         doctorRespository.deleteById(id);
         return true;
     }
 }
