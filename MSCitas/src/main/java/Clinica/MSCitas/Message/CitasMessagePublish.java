package Clinica.MSCitas.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Clinica.MSCitas.Model.ModelDiagnosticoCita;

@Component
public class CitasMessagePublish {
    
 @Value("${spring.kafka.template.default-topic}")
 String clinica;

 // private Logger log = LoggerFactory.getLogger(PayMessagePublish.class);

 @Autowired
 KafkaTemplate<Integer, String> kafkaTemplate;

 @Autowired
 ObjectMapper objectMapper;

 public void sendUpdateDiagnosticoEvent(ModelDiagnosticoCita modelDiagnosticoCita) throws JsonProcessingException {

     String value = objectMapper.writeValueAsString(modelDiagnosticoCita);
     kafkaTemplate.send(clinica, value);
 }
}
