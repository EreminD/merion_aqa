package ru.merion.aqa.lesson23.db.jpa;

import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;
import org.hibernate.jpa.HibernatePersistenceProvider;
import ru.merion.aqa.lesson23.db.model.CompanyEntity;

import javax.sql.DataSource;
import java.net.URL;
import java.util.List;
import java.util.Properties;


public class MyPersistenceUnitInfo implements PersistenceUnitInfo {
    private final Properties props;

    public MyPersistenceUnitInfo(Properties props) {
        this.props = props;
    }

    @Override
    public List<String> getManagedClassNames() {
        return List.of(
                CompanyEntity.class.getName()
                // Author.class.getName(),
                // Book.class.getName(),
                // Chapter.class.getName(),
                // Genre.class.getName(),
                // Section.class.getName()
        );
    }

    @Override
    public Properties getProperties() {
        return this.props;
    }

    @Override
    public String getPersistenceUnitName() {
        return "TestUnit";
    }

    @Override
    public String getPersistenceProviderClassName() {
        return HibernatePersistenceProvider.class.getName();
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return null;
    }

    @Override
    public DataSource getJtaDataSource() {
        return null;
    }

    @Override
    public DataSource getNonJtaDataSource() {
        return null;
    }

    @Override
    public List<String> getMappingFileNames() {
        return null;
    }

    @Override
    public List<URL> getJarFileUrls() {
        return null;
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }

    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        return null;
    }

    @Override
    public ValidationMode getValidationMode() {
        return null;
    }

    @Override
    public String getPersistenceXMLSchemaVersion() {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public void addTransformer(ClassTransformer transformer) {

    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        return null;
    }
}
