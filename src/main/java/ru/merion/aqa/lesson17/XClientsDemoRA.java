package ru.merion.aqa.lesson17;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.merion.aqa.lesson15.model.Company;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

public class XClientsDemoRA {

    public static void main(String[] args) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        XClientsWebClientRA clientRA = new XClientsWebClientRA("https://x-clients-be.onrender.com");

        String token = clientRA.getToken("leonardo", "leads");
        int id = clientRA.create("Demo", "RestAssured", token);

        Company company = clientRA.getById(id);
        System.out.println(company.id());
        System.out.println(company.name());

        List<Company> all = clientRA.getAll();
        all.forEach(c -> System.out.println(c.name()));
    }
}
