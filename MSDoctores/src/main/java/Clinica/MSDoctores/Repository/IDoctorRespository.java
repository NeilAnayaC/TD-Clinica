 package Clinica.MSDoctores.Repository;

 import org.springframework.data.mongodb.repository.MongoRepository;
 import org.springframework.stereotype.Repository;

 import Clinica.MSDoctores.Model.DoctorModel;

 @Repository
 public interface IDoctorRespository extends MongoRepository<DoctorModel, String>{
    
 }
