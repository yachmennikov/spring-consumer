package com.course.kafkaproducer.producer;

import com.course.kafkaproducer.entity.CarLocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CarLocationProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(CarLocation carLocation) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(carLocation);
        kafkaTemplate.send("t_location_2",carLocation.getCarId(), json);
    }
}
