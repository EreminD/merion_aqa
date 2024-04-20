package ru.merion.aqa.lesson15;

import okhttp3.*;
import ru.merion.aqa.lesson15.model.Company;

import java.io.IOException;
import java.util.List;

public class XClientsDemo {

    public static final String URL = "https://x-clients-be.onrender.com";

    public static void main(String[] args) throws IOException {

        XClientsWebClient service = new XClientsWebClient(URL);

        String token = service.getToken("leonardo", "leads");

        int newCompanyID = service.create("ABC", "letters", token);

        List<Company> companyList = service.getAll(false);
    }
}
