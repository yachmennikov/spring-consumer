package com.course.kafkaproducer.scheduler;

import com.course.kafkaproducer.entity.CarLocation;
import com.course.kafkaproducer.producer.CarLocationProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CarLocationScheduler {

    private final static Logger logger = LoggerFactory.getLogger(CarLocationScheduler.class);

    private CarLocation car1;
    private CarLocation car2;
    private CarLocation car3;

    @Autowired
    private CarLocationProducer carLocationProducer;

    public CarLocationScheduler() {
        var now = System.currentTimeMillis();

        car1 = new CarLocation("car-one", now, 0);
        car2 = new CarLocation("car-two", now, 110);
        car3 = new CarLocation("car-three", now, 95);
    }

    @Scheduled(fixedRate = 10000)
    public void generateCarLocation() throws JsonProcessingException {
        var now = System.currentTimeMillis();

        car1.setTimestamp(now);
        car2.setTimestamp(now);
        car3.setTimestamp(now);

        car1.setDistance(car1.getDistance() + 1);
        car2.setDistance(car2.getDistance() - 1);
        car3.setDistance(car3.getDistance() + 1);

        carLocationProducer.sendMessage(car1);
        carLocationProducer.sendMessage(car2);
        carLocationProducer.sendMessage(car3);
    }

}
