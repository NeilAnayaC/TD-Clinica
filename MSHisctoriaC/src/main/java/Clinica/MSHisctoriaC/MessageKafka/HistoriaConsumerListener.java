package Clinica.MSHisctoriaC.MessageKafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Clinica.MSHisctoriaC.dto.HistoriaRequest;
import Clinica.MSHisctoriaC.model.modelHistoria;
import Clinica.MSHisctoriaC.service.IDetalleService;
import Clinica.MSHisctoriaC.service.IHistoriaService;

@Component
public class HistoriaConsumerListener {
    
    @Autowired
    IHistoriaService historiaService;

    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void OnMessage(ConsumerRecord<String, String> consumerRecord)
            throws JsonMappingException, JsonProcessingException {
       // log.info("****************************************************************");
       // log.info("ConsumerRecord : {}", consumerRecord.value());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage = consumerRecord.value();
        HistoriaRequest data = objectMapper.readValue(jsonMessage, HistoriaRequest.class);

        modelHistoria model = historiaService.findById(data.getIdhistoria());

        if (model.getDiagnostico().equals(data.getDiagnostico())) {
            model.setDiagnostico(jsonMessage);
        }

        historiaService.update(model);

    }
}
