package ru.merion.aqa.lesson26.owner;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:db.${myEnv}.properties"})
public interface TestConfig extends Config {
    @Key("connection.driver_class")
    String driverClass();

    @Key("connection.url")
    String url();

    @Key("connection.username")
    String username();

    @Key("connection.password")
    String password();

    @Key("show_sql")
    boolean showSql();

    @DefaultValue("1")
    @Key("max_connections")
    int maxConnections();
}
