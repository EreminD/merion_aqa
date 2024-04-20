package ru.merion.aqa.lesson24.ext;

import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;
import ru.merion.aqa.lesson7.WDFactory;

public class DriverResolver implements ParameterResolver, AfterEachCallback {

    private WebDriver driver;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(WebDriver.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
         driver = WDFactory.create();
         return driver;
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        if (driver!=null){
            driver.quit();
        }
    }
}
