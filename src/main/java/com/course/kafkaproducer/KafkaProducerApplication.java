package com.course.kafkaproducer;

import com.course.kafkaproducer.entity.Image;
import com.course.kafkaproducer.producer.FoodOrderProducer;
import com.course.kafkaproducer.producer.ImageProducer;
import com.course.kafkaproducer.producer.InvoiceProducer;
import com.course.kafkaproducer.service.ImageService;
import com.course.kafkaproducer.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableScheduling
public class KafkaProducerApplication implements CommandLineRunner {

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private InvoiceProducer invoiceProducer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 10; i++) {
			var invoice = invoiceService.generateInvoice();

			if (i >= 5) {
				invoice.setAmount(-1);
			}

			invoiceProducer.sendMessage(invoice);
		}
	}
}
