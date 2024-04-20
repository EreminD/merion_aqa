package ru.merion.aqa.lesson23;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;
import ru.merion.aqa.lesson23.db.jpa.MyPersistenceUnitInfo;
import ru.merion.aqa.lesson23.db.model.CompanyEntity;

import java.util.List;
import java.util.Properties;

public class EntityManagerDemo {
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.connection.url", "jdbc:postgresql://dpg-cn1542en7f5s73fdrigg-a.frankfurt-postgres.render.com/x_clients_xxet");
        properties.put("hibernate.connection.username", "x_clients_user");
        properties.put("hibernate.connection.password", "x7ngHjC1h08a85bELNifgKmqZa8KIR40");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.connection.autocommit", "true");

        PersistenceUnitInfo info = new MyPersistenceUnitInfo(properties);
        HibernatePersistenceProvider hibernatePersistenceProvider = new HibernatePersistenceProvider();
        EntityManagerFactory factory = hibernatePersistenceProvider.createContainerEntityManagerFactory(info, info.getProperties());
        EntityManager entityManager = factory.createEntityManager();

        CompanyEntity company = entityManager.find(CompanyEntity.class, 3014);
        System.out.println(company);

        CompanyEntity newCompany = new CompanyEntity();
        newCompany.setCreated();
        newCompany.setName("Java test");
        newCompany.setDescription("hibernate autocommit");
        newCompany.setChanged();


        entityManager.getTransaction().begin();
        entityManager.persist(newCompany);
        System.out.println(newCompany);

        CompanyEntity edited = entityManager.find(CompanyEntity.class, 3014);
        edited.setDescription("Updated");

        entityManager.merge(edited);
        entityManager.remove(newCompany);

        // получить все организации, где is_active == false
        String sql = "SELECT c FROM CompanyEntity c WHERE c.deleted is null AND c.isActive=:active";
        TypedQuery<CompanyEntity> query = entityManager.createQuery(sql, CompanyEntity.class);
        query.setParameter("active", false);
        List<CompanyEntity> list = query.getResultList();

    }
}
