package ru.merion.aqa.lesson17;

import io.restassured.RestAssured;
import io.restassured.config.FailureConfig;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredDemo {
    public static final String URL = "https://todo-app-sky.herokuapp.com";

    public static void main(String[] args) {

        RestAssured.baseURI = URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();


        given()
                .when().get("")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

        String json = "{\"title\":\"456\",\"completed\":false}";

        given()
                .body(json)
                .contentType(ContentType.JSON)
                .when().post()
                .then()
                .onFailMessage("Не смогли создать задачу")
                .statusCode(201)
                .header("Connection", "keep-alive")
                .body("title", equalTo("4567"));
    }
}
