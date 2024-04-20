package ru.merion.aqa.lesson19;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoanService {
//Ссылка на проект с урока https://github.com/EreminD/kafka-tests
    private KafkaProducer<String, String> producer;
    @BeforeEach
    public void setUp() throws UnknownHostException {
        Properties properties = new Properties();
        properties.put("client.id", InetAddress.getLocalHost().getHostName());
        properties.put("bootstrap.servers", "localhost:29092");
        properties.put("acks", "all");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(properties);
    }

    @AfterEach
    public void tearDown(){
        producer.close();
    }

    @Test
    public void shouldUseLastPriceOnGetRates(){
        String url = "http://localhost:8082/loan";
        float priceToBe = 99.88f;

        // 1. Publish message
        String message = "{\"currency\":\"USD\",\"price\":" + priceToBe + "}";
        ProducerRecord<String, String> record = new ProducerRecord<>("prices", "", message);
        record.headers().add("__TypeId__", "com.example.Price".getBytes());
        producer.send(record);

        // 2. GET
        // 3. HTTP Response contains step 1 info
        given()
                .get(url)
                .then()
                .statusCode(200)
                .body("basePrice.currency", equalTo("USD"))
                .body("basePrice.price", equalTo(priceToBe));


    }
}
