package ru.merion.aqa.lesson19;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProducerTest {
    //Ссылка на проект с урока https://github.com/EreminD/kafka-tests
    private KafkaConsumer<String, String> consumer;

    @BeforeEach
    public void setUp() throws UnknownHostException {
        Properties properties = new Properties();
        properties.put("client.id", InetAddress.getLocalHost().getHostName());
        properties.put("group.id", "autotest");
        properties.put("enable.auto.commit", "true");
        properties.put("bootstrap.servers", "localhost:29092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(properties);
    }

    @AfterEach
    public void tearDown() {
        consumer.unsubscribe();
        consumer.close();
    }

    @Test
    public void shouldPublishPrice() throws UnknownHostException {
        float priceToBe = 88.88f;
        String url = "http://localhost:8081/rate/{priceToBe}";

        given()
                .get(url, priceToBe)
                .then()
                .statusCode(200)
                .body("currency", equalTo("USD"))
                .body("price", equalTo(priceToBe));

        // 3. Receive price and check
        String message = awaitMessage(
                "prices",
                10000L,
                msg -> msg.value().contains(Float.toString(priceToBe)
                )
        );

        assertNotNull(message);
        // ObjectMapper
        assertEquals("{\"currency\":\"USD\",\"price\":" + priceToBe + "}", message);
    }

    private String awaitMessage(String topicName, long timeout, Predicate<ConsumerRecord<String, String>> predicate) {
        consumer.subscribe(List.of(topicName));
        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < timeout) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10));
            for (ConsumerRecord<String, String> record : records) {
                if (predicate.test(record)) {
                    return record.value();
                }
            }
        }


        return null;
    }
}
