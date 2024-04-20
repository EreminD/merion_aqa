package ru.merion.aqa.lesson23;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.merion.aqa.ext.ClientProvider;
import ru.merion.aqa.ext.Token;
import ru.merion.aqa.lesson15.XClientsWebClient;
import ru.merion.aqa.lesson15.model.Company;
import ru.merion.aqa.lesson23.db.CompanyRepository;
import ru.merion.aqa.lesson23.db.model.CompanyEntity;
import ru.merion.aqa.lesson23.ext.JpaRepositoryResolver;
import ru.merion.aqa.lesson23.ext.TokenProvider;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({ClientProvider.class, TokenProvider.class, JpaRepositoryResolver.class})
public class XClientsBusinessTest {
    private int companyId;

    @AfterEach
    public void tearDown(CompanyRepository repository) throws SQLException {
        repository.deleteById(companyId);
    }

    @Test
    @DisplayName("Проверяем, что можно получить список организаций")
    public void shouldReturnList(XClientsWebClient client, CompanyRepository repository) throws IOException, SQLException {
        List<CompanyEntity> listViaDb = repository.getAll();
        List<Company> listViaApi = client.getAll();

        Set<Integer> dbIdSet = listViaDb.stream().map(CompanyEntity::getId).collect(Collectors.toSet());
        Set<Integer> apiIdSet = listViaApi.stream().map(Company::id).collect(Collectors.toSet());

        assertEquals(dbIdSet, apiIdSet);
        assertEquals(listViaDb.size(), listViaApi.size());
    }

    @DisplayName("Проверяем, что можно получить список организаций")
    @ParameterizedTest(name = "{index} -> У которых is_active = {0}")
    @ValueSource(booleans = {true, false})
    public void shouldReturnNotActiveList(boolean val, XClientsWebClient client, CompanyRepository repository) throws IOException, SQLException {
        long countViaDb = repository.count(val);
        int countViaApi = client.getAll(val).size();



        assertEquals(countViaDb, countViaApi);
    }

    @Test
    @DisplayName("Проверяем, что можно получить инфу по конкретной организации")
    public void shouldReturnCompany(XClientsWebClient client, CompanyRepository repository) throws IOException, SQLException {
        String name = "New Comp Name";
        String description = "New Comp Description";
        companyId = repository.create(name, description);
        Company company = client.getById(companyId);

        assertEquals(companyId, company.id());
        assertEquals(name, company.name());
        assertEquals(description, company.description());
        assertTrue(company.isActive());
    }

    @Test
    @DisplayName("Проверяем, что можно создавать организации")
    public void shouldCreateCompany(XClientsWebClient xClient,
                                    @Token(createNewUser = true) String token,
                                    CompanyRepository repository) throws IOException, SQLException {
        // создать организацию
        String companyName = "DeleteMe";
        String companyDescription = "Description";
        companyId = xClient.create(companyName, companyDescription, token);

        // запросить инфу из БД
        CompanyEntity company = repository.getById(companyId);
        System.out.println(company);

        // сравнить поля в БД с инфой из запроса
        assertNotNull(company, "Из БД вернулось 0 строк");
        assertEquals(companyId, company.getId());
        assertEquals(companyName, company.getName());
        assertEquals(companyDescription, company.getDescription());
        assertTrue(company.isActive());
        assertNull(company.getDeleted());
    }

    @Test
    @DisplayName("Проверяем, что is_active по дефолту равен true, description == null, если не передавали")
    public void shouldSetDefaultValues(XClientsWebClient xClient, @Token(login = "leonardo", pass = "leads") String token, CompanyRepository repository) throws IOException, SQLException {
        // создать организацию
        String companyName = "DeleteMe";
        companyId = xClient.create(companyName, null, token);

        // запросить инфу из БД
        CompanyEntity companyDb = repository.getById(companyId);

        // сравнить поля в БД с инфой из запроса
        assertTrue(companyDb.isActive());
        assertNull(companyDb.getDescription());
    }

    @Test
    @DisplayName("Можем удалить организацию")
    public void shouldDeleteCompany(
            XClientsWebClient client,
            @Token(login = "leonardo", pass = "leads") String token,
            CompanyRepository repository
    ) throws IOException, SQLException {
        String name = "CompanyName";
        String desc = "CompanyDesc";
        companyId = repository.create(name, desc);

        Company deletedInfo = client.deleteById(companyId, token);
        CompanyEntity companyDb = repository.getById(companyId);

        assertEquals(companyId, deletedInfo.id());
        assertEquals(name, deletedInfo.name());
        assertEquals(desc, deletedInfo.description());
        assertTrue(deletedInfo.isActive());
        assertNotNull(companyDb.getDeleted());
    }

    @Test
    @DisplayName("Можем редактировать организацию")
    public void shouldUpdateCompany(
            XClientsWebClient client,
            @Token(login = "leonardo", pass = "leads") String token,
            CompanyRepository repository
    ) throws IOException, SQLException {
        String name = "CompanyName";
        String desc = "CompanyDesc";
        String newDesc = "NewCompanyDesc";
        companyId = repository.create(name, desc);

        client.updateCompany(companyId, name, newDesc, token);

        CompanyEntity company = repository.getById(companyId);
        System.out.println(company);

        // сравнить поля в БД с инфой из запроса
        assertNotNull(company, "Из БД вернулось 0 строк");
        assertEquals(companyId, company.getId());
        assertEquals(name, company.getName());
        assertEquals(newDesc, company.getDescription());
        assertTrue(company.isActive());
        assertNull(company.getDeleted());
    }

    @Test
    @DisplayName("Можем деактивировать организацию. is_active должен быть false")
    public void shouldDeactivateCompany(
            XClientsWebClient client,
            @Token(login = "leonardo", pass = "leads") String token,
            CompanyRepository repository
    ) throws IOException, SQLException {
        String name = "CompanyName";
        String desc = "CompanyDesc";
        companyId = repository.create(name, desc);

        client.setActive(companyId, false, token);

        CompanyEntity company = repository.getById(companyId);
        System.out.println(company);

        // сравнить поля в БД с инфой из запроса
        assertFalse(company.isActive());
        assertNotNull(company, "Из БД вернулось 0 строк");
        assertEquals(companyId, company.getId());
        assertEquals(name, company.getName());
        assertEquals(desc, company.getDescription());
        assertNull(company.getDeleted());
    }


}