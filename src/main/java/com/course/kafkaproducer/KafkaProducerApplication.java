package com.course.kafkaproducer;

import com.course.kafkaproducer.entity.FoodOrder;
import com.course.kafkaproducer.entity.SimpleNumber;
import com.course.kafkaproducer.producer.FoodOrderProducer;
import com.course.kafkaproducer.producer.SimpleNumberProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaProducerApplication implements CommandLineRunner {

	@Autowired
	private FoodOrderProducer foodOrderProducer;

	@Autowired
	private SimpleNumberProducer simpleNumberProducer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var chickenOrder = new FoodOrder(3, "Chicken");
		var fishOrder = new FoodOrder(10, "Fish");
		var pizzaOrder = new FoodOrder(5, "Pizza");

		foodOrderProducer.sendMessage(chickenOrder);
		foodOrderProducer.sendMessage(fishOrder);
		foodOrderProducer.sendMessage(pizzaOrder);

		// ..............simple numbers...............
		for (int i = 100; i < 103; i++) {
			var simpleNumber = new SimpleNumber(i);
			simpleNumberProducer.sendMessage(simpleNumber);
		}
	}
}
