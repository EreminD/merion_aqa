package ru.merion.aqa.lesson16.business;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.merion.aqa.ext.ClientProvider;
import ru.merion.aqa.ext.Token;
import ru.merion.aqa.ext.TokenProvider;
import ru.merion.aqa.lesson15.XClientsWebClient;
import ru.merion.aqa.lesson15.model.Company;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({ClientProvider.class, TokenProvider.class})
public class XClientsBusinessTest {
    private int companyId;

    @AfterEach
    public void tearDown(XClientsWebClient client, @Token(login = "leonardo", pass = "leads") String token) throws IOException {
        client.deleteById(companyId, token);
    }

    @Test
    public void shouldCreateCompany(XClientsWebClient xClient, @Token(login = "leonardo", pass = "leads") String token) throws IOException {
        // посмотреть, сколько было ДО
        int sizeBefore = xClient.getAll().size();

        // изменить
        companyId = xClient.create("DeleteMe", "", token);

        // посмотреть, сколько стало ПОСЛЕ
        int sizeAfter = xClient.getAll().size();
        assertEquals(sizeBefore + 1, sizeAfter);
    }

    @Test
    public void shouldSetDefaultValues(XClientsWebClient xClient, @Token(login = "leonardo", pass = "leads") String token) throws IOException {
        String companyName = "DeleteMe";
        companyId = xClient.create(companyName, "", token);

        Company company = xClient.getById(companyId);

        assertEquals(companyId, company.id());
        assertTrue(company.isActive());
        assertTrue(company.description().isBlank());
        assertEquals(companyName, company.name());
    }

    @Test
    public void shouldSaveNameAndDescValues(XClientsWebClient xClient, @Token(login = "leonardo", pass = "leads") String token) throws IOException {
        String companyName = "DeleteMe";
        String desc = "please";

        companyId = xClient.create(companyName, desc, token);

        Company company = xClient.getById(companyId);

        assertEquals(companyId, company.id());
        assertTrue(company.isActive());
        assertEquals(desc, company.description());
        assertEquals(companyName, company.name());
    }

    @Test
    public void shouldDeleteCompany(
            XClientsWebClient client,
            @Token(login = "leonardo", pass = "leads") String token
    ) throws IOException {
        int id = client.create("A", "B", token);
        Company deletedInfo = client.deleteById(id, token);
        assertEquals(id, deletedInfo.id());
    }
}


