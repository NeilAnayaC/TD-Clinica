 package Clinica.MSDoctores.Service;

 import java.util.List;

import Clinica.MSDoctores.Model.DoctorModel;

 public interface IDoctorService {
    
     public DoctorModel add(DoctorModel doctor);
     public DoctorModel findById(String id);
     public List<DoctorModel> findAll();
     public DoctorModel update(DoctorModel doctor);
     public boolean delete(String id);
    

 }
