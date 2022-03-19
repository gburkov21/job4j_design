package ru.job4j.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("jdbc.url");
        String login = properties.getProperty("jdbc.login");
        String password = properties.getProperty("jdbc.password");
        this.connection = DriverManager.getConnection(url, login, password);

    }

    public void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("create table if not exists %s();", tableName);
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("drop table if exists %s;", tableName);
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table %s add if not exists %s %s;", tableName, columnName, type);
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table %s DROP COLUMN IF EXISTS %s;", tableName, columnName);
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName);
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        try (FileReader fileReader = new FileReader("./jdbc/app.properties")) {
            Properties properties = new Properties();
            properties.load(fileReader);
            TableEditor tableEditor = new TableEditor(properties);
            String tableName = "myTable";

            tableEditor.createTable(tableName);
            System.out.println(getTableScheme(tableEditor.connection, tableName));

            tableEditor.addColumn(tableName, "id", "serial primary key");
            System.out.println(getTableScheme(tableEditor.connection, tableName));

            tableEditor.addColumn(tableName, "name", "varchar(255)");
            System.out.println(getTableScheme(tableEditor.connection, tableName));

            tableEditor.addColumn(tableName, "age", "int");
            System.out.println(getTableScheme(tableEditor.connection, tableName));

            tableEditor.renameColumn(tableName, "name", "surname");
            System.out.println(getTableScheme(tableEditor.connection, tableName));

            tableEditor.dropColumn(tableName, "age");
            System.out.println(getTableScheme(tableEditor.connection, tableName));

            tableEditor.dropColumn(tableName, "surname");
            System.out.println(getTableScheme(tableEditor.connection, tableName));

            tableEditor.dropColumn(tableName, "id");
            System.out.println(getTableScheme(tableEditor.connection, tableName));

            tableEditor.dropTable(tableName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
