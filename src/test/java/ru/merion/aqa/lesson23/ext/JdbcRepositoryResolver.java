package ru.merion.aqa.lesson23.ext;

import org.junit.jupiter.api.extension.*;
import ru.merion.aqa.lesson23.db.CompanyRepository;
import ru.merion.aqa.lesson23.db.jdbc.CompanyRepositoryJdbc;

import java.sql.Connection;

public class JdbcRepositoryResolver implements ParameterResolver {
    private CompanyRepository repository;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CompanyRepository.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        if (repository == null) {
            Connection connection = (Connection) extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).get("db_connection");
            repository = new CompanyRepositoryJdbc(connection);
        }
        return repository;
    }
}
