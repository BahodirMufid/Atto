package com.company.repository;

import com.company.db.DbConnection;
import com.company.dto.ProfileDto;
import com.company.enums.ProfileStatus;
import com.company.enums.ProfileType;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ProfileRepository {
    public static DbConnection dbConnection = null;

    public void createProfile(ProfileDto profileDto) {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into profile (name,surname,phone,parol,create_date,status,type) " +
                    "values(?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, profileDto.getName());
            ps.setString(2, profileDto.getSurname());
            ps.setString(3, profileDto.getPhone());
            ps.setString(4,profileDto.getPassword());
            ps.setTimestamp(5,Timestamp.valueOf(profileDto.getCreate_date()));
            ps.setString(6,profileDto.getStatus().name());
            ps.setString(7,profileDto.getType().name());
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

    public ProfileDto getProfileByPhone(String  phone){
        ProfileDto profileDto = new ProfileDto();
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select * from profile where phone = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,phone);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                profileDto.setId(resultSet.getInt("id"));
                profileDto.setName(resultSet.getString("name"));
                profileDto.setSurname(resultSet.getString("surname"));
                profileDto.setPhone(resultSet.getString("phone"));
                profileDto.setPassword(resultSet.getString("parol"));
                profileDto.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
                profileDto.setType(ProfileType.valueOf(resultSet.getString("type")));
                profileDto.setCreate_date(resultSet.getTimestamp("create_date").toLocalDateTime());
                return profileDto;
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

    public List<ProfileDto> getProfileList() {
        List<ProfileDto> list = new LinkedList<>();
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select * from profile ";
            ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                ProfileDto profile = new ProfileDto();
                profile.setId(resultSet.getInt("id"));
                profile.setName(resultSet.getString("name"));
                profile.setSurname(resultSet.getString("surname"));
                profile.setPassword(resultSet.getString("parol"));
                profile.setCreate_date(resultSet.getTimestamp("create_date").toLocalDateTime());
                profile.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
                profile.setType(ProfileType.valueOf(resultSet.getString("type")));
                list.add(profile);
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

    public void updateStatus(ProfileDto profileDto) {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update profile set status = ? where phone = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, profileDto.getStatus().name());
            ps.setString(2, profileDto.getPhone());
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
