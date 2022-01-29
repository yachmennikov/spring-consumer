package com.course.kafkaproducer;

import com.course.kafkaproducer.entity.Image;
import com.course.kafkaproducer.producer.FoodOrderProducer;
import com.course.kafkaproducer.producer.ImageProducer;
import com.course.kafkaproducer.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableScheduling
public class KafkaProducerApplication implements CommandLineRunner {

	@Autowired
	private ImageService imageService;

	@Autowired
	private ImageProducer imageProducer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var image1 = imageService.generateImage("jpg");
		var image2 = imageService.generateImage("svg");
		var image3 = imageService.generateImage("png");

		imageProducer.sendMessage(image1);
		imageProducer.sendMessage(image2);
		imageProducer.sendMessage(image3);
	}
}
