package ru.merion.aqa.lesson16.contract;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.merion.aqa.lesson15.MyCustomLogger;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class XClientsCompanyTest {
    private OkHttpClient client;
    private ObjectMapper mapper;
    private final MediaType JSON = MediaType.get("application/json");
    public static final String URL = "https://x-clients-be.onrender.com/company";
    public static final String URL_LOGIN = "https://x-clients-be.onrender.com/auth/login";
    private final static String X_CLIENT_TOKEN = "x-client-token";

    @BeforeEach
    public void setUp() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new MyCustomLogger());
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void shouldReturnArrayOnGetCompanyList() throws IOException {
        Request request = new Request.Builder().url(URL).build();
        Response response = client.newCall(request).execute();
        String body = response.body().string();

        assertEquals(200, response.code());
        assertTrue(body.startsWith("["));
        assertTrue(body.endsWith("]"));
    }

    @Test
    public void shouldReturn401WithoutToken() throws IOException {
        String json = """
                {
                  "name": "Contract Test Company",
                  "description": "string"
                }
                """;

        RequestBody body = RequestBody.create(json, MediaType.get("application/json"));
        Request request = new Request.Builder().url(URL).post(body).build();

        Response response = client.newCall(request).execute();

        assertEquals(401, response.code());
        assertEquals("{\"statusCode\":401,\"message\":\"Unauthorized\"}", response.body().string());
    }

    @Test
    public void shouldReturn401WithoutValidToken() throws IOException {
        String json = """
                {
                  "name": "Contract Test Company",
                  "description": "string"
                }
                """;

        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(URL)
                .header(X_CLIENT_TOKEN, "NON_VALID_TOKEN")
                .post(body).build();

        Response response = client.newCall(request).execute();

        assertEquals(401, response.code());
        assertEquals("{\"statusCode\":401,\"message\":\"Unauthorized\"}", response.body().string());
    }

    @Test
    public void shouldReturn201OnCompanyCreated() throws IOException {
        String json = """
                {
                  "name": "Contract Test Company",
                  "description": "string"
                }
                """;

        RequestBody reqBody = RequestBody.create(json, JSON);
        Request createRequest = new Request.Builder()
                .url(URL)
                .header(X_CLIENT_TOKEN, getToken())
                .post(reqBody).build();

        Response response = client.newCall(createRequest).execute();

        JsonNode jsonNode = mapper.readTree(response.body().string());
        int newId = jsonNode.get("id").asInt();

        assertEquals(201, response.code());
        assertTrue(newId > 0);
    }

    @Test
    public void shouldDeleteCompany() throws IOException {
        int id = createDummyCompany();

        Request request = new Request.Builder()
                .url(URL + "/delete/" + id)
                .header(X_CLIENT_TOKEN, getToken())
                .build();

        Response response = client.newCall(request).execute();

        String body = response.body().string();
        JsonNode node = mapper.readTree(body);

        assertEquals(200, response.code());
        assertEquals(id, node.get("id").asInt());
    }

    @Test
    @Tag("defect")
    public void shouldGet404OnDeleteNonExistedCompany() throws IOException {
        int id = createDummyCompany();

        Request request = new Request.Builder()
                .url(URL + "/delete/" + id)
                .header(X_CLIENT_TOKEN, getToken())
                .build();
        client.newCall(request).execute();

        Response response = client.newCall(request).execute();

        assertTrue(response.body().string().isEmpty());
        assertEquals(404, response.code());
    }


    @Test
    public void shouldReturn401OnDeleteCompany() throws IOException {
        int id = createDummyCompany();

        Request request = new Request.Builder()
                .url(URL + "/delete/" + id)
                .build();

        Response response = client.newCall(request).execute();

        assertEquals(401, response.code());
        assertEquals("{\"statusCode\":401,\"message\":\"Unauthorized\"}", response.body().string());

    }

    private String getToken() throws IOException {
        String json = """
                {
                  "username": "leonardo",
                  "password": "leads"
                }
                """;

        RequestBody authBody = RequestBody.create(json, JSON);
        Request request = new Request.Builder().post(authBody).url(URL_LOGIN).build();
        Response response = client.newCall(request).execute();

        String body = response.body().string();

        JsonNode jsonNode = mapper.readTree(body);
        return jsonNode.get("userToken").asText();
    }

    private int createDummyCompany() throws IOException {
        String json = """
                {
                  "name": "Will be deleted",
                  "description": "string"
                }
                """;

        RequestBody reqBody = RequestBody.create(json, JSON);
        Request createRequest = new Request.Builder()
                .url(URL)
                .header(X_CLIENT_TOKEN, getToken())
                .post(reqBody).build();

        Response response = client.newCall(createRequest).execute();

        JsonNode jsonNode = mapper.readTree(response.body().string());
        return jsonNode.get("id").asInt();
    }




}
