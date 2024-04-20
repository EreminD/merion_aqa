package ru.merion.aqa.lesson26.default_props;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class PropsDemo {

    public static void main(String[] args) throws IOException, SQLException {
        String env = System.getProperty("environment", "test"); // test || uat
        String fileName = "db." + env + ".properties";

        Properties properties = new Properties();
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        properties.load(new FileInputStream(path + fileName));

//        Connection connection = DriverManager.getConnection(properties.getProperty("connection.url"), properties.getProperty("connection.username"), properties.getProperty("connection.password"));

        String maxConnections = properties.getProperty("max_connections");
        int max = Integer.parseInt(maxConnections);

        System.out.println(properties.size());
    }
}
