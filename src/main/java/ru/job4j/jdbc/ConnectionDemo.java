package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.*;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config config = new Config("./jdbc/app.properties");
        config.load();
        Class.forName(config.value("jdbc.driver"));
        String url = config.value("jdbc.url");
        String login = config.value("jdbc.login");
        String password = config.value("jdbc.password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
            System.out.println(metaData.getDriverName());
        }
    }
}
