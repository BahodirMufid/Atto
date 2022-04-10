package com.company.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public  Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/atto_db",
                    "postgres","Bahodir2011");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
