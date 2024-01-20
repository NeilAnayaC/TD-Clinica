 package Clinica.MSDoctores.Model;

 import java.io.Serializable;

 import org.bson.codecs.pojo.annotations.BsonId;
 import org.springframework.data.mongodb.core.mapping.Document;

 @Document(collection = "doctor")
 public class DoctorModel implements Serializable{
    

     private static final long serialVersionUID = 1L;

     @BsonId
     private String id;

     private String nombred;

     private String apellidod;

     private String especialidadd;

     private String celular;

     private String correo;

     public String getId() {
         return id;
     }

     public void setId(String id) {
         this.id = id;
     }

     public String getNombred() {
         return nombred;
     }

     public void setNombred(String nombred) {
         this.nombred = nombred;
     }

     public String getApellidod() {
         return apellidod;
     }

     public void setApellidod(String apellidod) {
         this.apellidod = apellidod;
     }

     public String getEspecialidadd() {
         return especialidadd;
     }

     public void setEspecialidadd(String especialidadd) {
         this.especialidadd = especialidadd;
     }

     public String getCelular() {
         return celular;
     }

     public void setCelular(String celular) {
         this.celular = celular;
     }

     public String getCorreo() {
         return correo;
     }

     public void setCorreo(String correo) {
         this.correo = correo;
     }

    
 }
