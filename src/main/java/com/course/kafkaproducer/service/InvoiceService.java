package com.course.kafkaproducer.service;

import com.course.kafkaproducer.entity.Invoice;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class InvoiceService {

    private static int counter = 0;

    public Invoice generateInvoice() {
        counter++;
        var number = "INVOICE-" + counter;
        var amount = ThreadLocalRandom.current().nextInt(1, 1000);
        return new Invoice(number, amount, "USD");
    }
}
