package ru.merion.aqa.ext;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import ru.merion.aqa.lesson15.XClientsWebClient;
import ru.merion.aqa.lesson17.XClientsWebClientRA;

public class ClientProviderRA implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(XClientsWebClientRA.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        XClientsWebClientRA client = new XClientsWebClientRA("https://x-clients-be.onrender.com");
        extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).put("x_client", client);
        return client;
    }
}
