package com.company.db;
// PROJECT NAME Atto
// TIME 10:15
// MONTH 04
// DAY 05

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTable {
    static DbConnection dbConnection;
    public static void create_table() {
        Connection connection = dbConnection.getConnection();
        Statement ps = null;
        try {
            String sql = " create table terminal( " +
                    "id serial primary key," +
                    "cod varchar(30)  not null ," +
                    "adress varchar(70) ," +
                    "status varchar(30) ," +
                    "create_date timestamp )";

            ps = connection.createStatement();
            System.out.println(ps.executeUpdate(sql));


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
