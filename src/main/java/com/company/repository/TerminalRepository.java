package com.company.repository;

import com.company.db.DbConnection;
import com.company.dto.Terminal;
import com.company.enums.TermStatus;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TerminalRepository {
    DbConnection dbConnection = null;

    public List<Terminal> getTermList(){
        List<Terminal> list = new LinkedList<>();
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select * from terminal ";
            ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                Terminal term = new Terminal();
                term.setId(resultSet.getInt("id"));
                term.setCod(resultSet.getString("cod"));
                term.setAdress(resultSet.getString("adress"));
                term.setCreat_date(resultSet.getTimestamp("create_date").toLocalDateTime());
                term.setStatus(TermStatus.valueOf(resultSet.getString("status")));
                list.add(term);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ps != null){
                    ps.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return list;
    }

    public Terminal getTerminal(String  cod){
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select * from terminal where cod = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,cod);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                Terminal term = new Terminal();
                term.setId(resultSet.getInt("id"));
                term.setAdress(resultSet.getString("adress"));
                term.setCreat_date(resultSet.getTimestamp("create_date").toLocalDateTime());
                term.setStatus(TermStatus.valueOf(resultSet.getString("status")));
                return term;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ps != null){
                    ps.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public void createTerm(Terminal terminal) {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into terminal (cod,adress,create_date,status) " +
                    "values(?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, terminal.getCod());
            ps.setString(2, terminal.getAdress());
            ps.setTimestamp(3, Timestamp.valueOf(terminal.getCreat_date()));
            ps.setString(4, terminal.getStatus().name());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ps != null){
                    ps.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        System.out.println("Success");
    }

    public void deleteTerminal(String cod){
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete  from terminal where cod = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,cod);
            ps.executeUpdate();
            System.out.println("Success");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ps != null){
                    ps.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    public void updateTerm(String  cod, String status) {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update terminal set status = ? where cod = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, cod);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ps != null){
                    ps.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        System.out.println("Success");
    }

    public void updateTermAdress(String cod, String adress) {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update terminal set adress = ? where cod = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, adress);
            ps.setString(2, cod);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ps != null){
                    ps.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        System.out.println("Success");
    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
}
