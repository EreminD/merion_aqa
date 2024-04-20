package ru.merion.aqa.lesson15;

import okhttp3.*;

import java.io.IOException;

public class OkHttpDemo {
    public static final String URL = "https://todo-app-sky.herokuapp.com/";

    public static void main(String[] args) throws IOException {
        // client
        OkHttpClient client = new OkHttpClient();

        // request
        Request getAllTasksReq = new Request.Builder().url(URL).build();
        Response response = client.newCall(getAllTasksReq).execute();

        System.out.println(response.code());
        MediaType mediaType = response.body().contentType();
        String body = response.body().string();
        System.out.println(body);

        String json = "{\"title\":\"456\",\"completed\":false}";
        RequestBody reqBody = RequestBody.create(json, MediaType.get("application/json"));
        Request createNewTaskRequest = new Request.Builder().url(URL).post(reqBody).header("ABC", "123").build();

        response = client.newCall(createNewTaskRequest).execute();
        System.out.println(response.code());
        System.out.println(response.body().string());
    }

}
