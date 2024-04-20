package ru.merion.aqa.lesson18_hw;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class EmployeeTests {
    private static final String LOGIN = "/auth/login";
    private static final String COMPANY = "company";
    private static final String EMPLOYEE = "employee";
    private static final String EMPLOYEE_ID = "employee/{id}";
    private static final String X_CLIENT_TOKEN = "x-client-token";
    private static final String URL = "https://x-clients-be.onrender.com";

    @BeforeAll
    public static void setAll() {
        RestAssured.baseURI = URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @DisplayName("Можно создать сотрудника в компании")
    public void iCanCreateAnEmployee() {
        // Создать компанию
        int newId = createNewCompany();
        String json = " { \"firstName\": \"Иван\", \"lastName\": \"Иванов\", \"companyId\": " + newId + ", \"phone\": \"+7923432423\"}";

        given().body(json).header(X_CLIENT_TOKEN, getToken()).contentType(ContentType.JSON).post(EMPLOYEE).then().statusCode(201).body("id", greaterThan(0));
    }

    @Test
    @DisplayName("Сотрудник отображается в списке сотрудников компании")
    public void iCanFindEmployeeInfo() {
        int newId = createNewCompany();

        int firstEmpId = createNewEmployee(newId);
        int secondEmpId = createNewEmployee(newId);

        given().queryParam("company", newId).get(EMPLOYEE).then().statusCode(200).body("size()", is(2)).body("[0].id", is(firstEmpId)).body("[1].id", is(secondEmpId));
    }

    @Test
    @DisplayName("Сотруднику можно изменить статус активности")
    public void iCanDeactiveteEmp() {
        int companyId = createNewCompany();
        int empId = createNewEmployee(companyId);

        String json = "{\"isActive\": false}";
        given().body(json).header(X_CLIENT_TOKEN, getToken()).contentType(ContentType.JSON).patch(EMPLOYEE + "/" + empId).then().statusCode(200);

        given().get(EMPLOYEE_ID, empId).then().body("isActive", is(false));
    }

    @Test
    @DisplayName("Сотруднику можно изменить email")
    public void iCanChangeEmail() {
        int companyId = createNewCompany();
        int empId = createNewEmployee(companyId);

        String json = "{\"email\": \"mail@mail.ru\"}";
        given().body(json).header(X_CLIENT_TOKEN, getToken()).contentType(ContentType.JSON).patch(EMPLOYEE + "/" + empId).then().statusCode(200);

        given().get(EMPLOYEE_ID, empId).then().body("email", is("mail@mail.ru"));
    }

    @Test
    @DisplayName("[BUG]. Сотруднику можно изменить email и телефон")
    public void iCanChangeEmailAndPhone() {
        int companyId = createNewCompany();
        int empId = createNewEmployee(companyId);

        String json = "{\"email\": \"mail@mail.ru\", \"phone\":\"+7765432456432\"}";
        given().body(json).header(X_CLIENT_TOKEN, getToken()).contentType(ContentType.JSON).patch(EMPLOYEE + "/" + empId).then().statusCode(200);

        given().get(EMPLOYEE + "/" + empId).then().body("email", is("mail@mail.ru")).body("phone", is("+7765432456432"));
    }

    @Test
    @DisplayName("Можно создать сотрудника в компании")
    public void iCantCreateEmployeeForNullCompany() {
        String json = " { \"firstName\": \"Иван\", \"lastName\": \"Иванов\", \"companyId\": " + Integer.MAX_VALUE + ", \"phone\": \"+7923432423\"}";
        given().body(json).header(X_CLIENT_TOKEN, getToken()).contentType(ContentType.JSON).post(EMPLOYEE).then().statusCode(500).body("statusCode", is(500)).body("message", is("Internal server error"));

        given().queryParam("company", Integer.MAX_VALUE).get(EMPLOYEE).then().statusCode(200).body("size()", is(0));
    }

    private String getToken() {
        String json = """
                {
                  "username": "leonardo",
                  "password": "leads"
                }
                """;

        return given()
                .body(json)
                .contentType(ContentType.JSON)
                .post(LOGIN)
                .then()
                .extract()
                .path("userToken");
    }

    private int createNewCompany() {
        String json = """
                {
                  "name": "Contract Test Company",
                  "description": "string"
                }
                """;

        return given().body(json).contentType(ContentType.JSON).header(X_CLIENT_TOKEN, getToken()).post(COMPANY).then().extract().path("id");
    }

    private int createNewEmployee(int companyId) {
        String json = "{\"firstName\": \"Иван\", \"lastName\": \"Иванов\", \"companyId\": " + companyId + ", \"phone\": \"+7923432423\"}";
        return given().body(json).contentType(ContentType.JSON).header(X_CLIENT_TOKEN, getToken()).post(EMPLOYEE).then().extract().path("id");
    }
}
