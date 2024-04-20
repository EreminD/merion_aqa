package ru.merion.aqa.lesson18.contract;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.model.ApiOperation;
import com.atlassian.oai.validator.model.Request;
import com.atlassian.oai.validator.model.Response;
import com.atlassian.oai.validator.report.LevelResolver;
import com.atlassian.oai.validator.report.ValidationReport;
import com.atlassian.oai.validator.restassured.OpenApiValidationFilter;
import com.atlassian.oai.validator.whitelist.ValidationErrorsWhitelist;
import com.atlassian.oai.validator.whitelist.rule.WhitelistRule;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.merion.aqa.lesson15.model.Company;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class XClientsCompanyTest {

    public static final String SWAGGER = "https://x-clients-be.onrender.com/docs-json";
    public static final String URL = "https://x-clients-be.onrender.com"; // getProperty
    public static final String COMPANY_ENDPOINT = "/company";
    public static final String DELETE_ENDPOINT = "/company/delete/{id}";
    public static final String LOGIN_ENDPOINT = "/auth/login";
    private final static String X_CLIENT_TOKEN = "x-client-token";
    private static ResponseSpecification RESPONSE_401;
    private static RequestSpecification CREATE_REQUEST;
    private static RequestSpecification DELETE_REQUEST;

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RESPONSE_401 = new ResponseSpecBuilder()
                .expectStatusCode(401)
                .expectBody("message", equalTo("Unauthorized"))
                .expectBody("statusCode", equalTo(401))
                .build();

        String json = """
                {
                  "name": "Contract Test Company",
                  "description": "string"
                }
                """;

        CREATE_REQUEST = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBody(json)
                .build();

        DELETE_REQUEST = new RequestSpecBuilder()
                .addHeader(X_CLIENT_TOKEN, getToken())
                .setBasePath(DELETE_ENDPOINT)
                .build();

        RestAssured.filters(new OpenApiValidationFilter(SWAGGER));
    }

    @Test
    public void shouldReturnArrayOnGetCompanyList() {
        List<Company> list = given()
                .get(COMPANY_ENDPOINT)
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("", Company.class);

        assertFalse(list.isEmpty());
    }

    @Test
    public void shouldReturn401WithoutValidToken() {
        given()
                .spec(CREATE_REQUEST)
                .header(X_CLIENT_TOKEN, "NO_VALID_TOKEN")
                .post(COMPANY_ENDPOINT)
                .then()
                .spec(RESPONSE_401);
    }

    @Test
    public void shouldReturn201OnCompanyCreated() {
        given()
                .spec(CREATE_REQUEST)
                .post(COMPANY_ENDPOINT)
                .then()
                .statusCode(201)
                .body("id", greaterThanOrEqualTo(0));
    }

    @Test
    public void shouldDeleteCompany() {
        int id = createDummyCompany();

        given()
                .header(X_CLIENT_TOKEN, getToken())
                .basePath(COMPANY_ENDPOINT)
                .get("/delete/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id));
    }

    @Test
    @Tag("defect")
    public void shouldGet404OnDeleteNonExistedCompany() {
        int id = createDummyCompany();

        given()
                .spec(DELETE_REQUEST)
                .get("", id)
                .andReturn();

        given()
                .spec(DELETE_REQUEST)
                .get("", id)
                .then()
                .statusCode(404);
    }

    @Test
    public void shouldReturn401OnDeleteCompany() {
        int id = createDummyCompany();

        given()
                .spec(DELETE_REQUEST)
                .header(X_CLIENT_TOKEN, "")
                .get("", id)
                .then()
                .spec(RESPONSE_401);
    }

    private static String getToken() {
        String creds = """
                {
                  "username": "leonardo",
                  "password": "leads"
                }
                """;


        return given()
                .body(creds)
                .contentType(ContentType.JSON)
                .post(LOGIN_ENDPOINT)
                .then()
                .statusCode(201)
                .extract().path("userToken");
    }

    private int createDummyCompany() {
        String json = """
                {
                  "name": "Contract Test Company",
                  "description": "string"
                }
                """;


        return given()
                .body(json)
                .contentType(ContentType.JSON)
                .header(X_CLIENT_TOKEN, getToken())
                .post(COMPANY_ENDPOINT)
                .then()
                .extract().path("id");
    }


}
