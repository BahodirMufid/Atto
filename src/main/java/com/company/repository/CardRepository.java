package com.company.repository;

import com.company.db.DbConnection;
import com.company.dto.Card;
import com.company.dto.ProfileCard;
import com.company.enums.CardStatus;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CardRepository {
   private static DbConnection dbConnection = null;
    public void createCard(Card card) {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into card (number,create_date,exp_date,status,balance) " +
                    "values(?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, card.getNumber());
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(3,card.getExpDate());
            ps.setString(4,card.getStatus().name());
            ps.setDouble(5,card.getBalance());
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

    public List<Card> getCardList(){
        List<Card> list = new LinkedList<>();
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select * from card ";
            ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                Card card = new Card();
                card.setId(resultSet.getInt("id"));
                card.setNumber(resultSet.getString("number"));
                card.setBalance(resultSet.getDouble("balance"));
                card.setCreate_date(resultSet.getTimestamp("create_date").toLocalDateTime());
                card.setStatus(CardStatus.valueOf(resultSet.getString("status")));
                card.setExpDate(resultSet.getString("exp_date"));
                list.add(card);
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

    public Card getCard(String  number){
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select * from card where number = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,number);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                Card card = new Card();
                card.setId(resultSet.getInt("id"));
                card.setNumber(resultSet.getString("number"));
                card.setBalance(resultSet.getDouble("balance"));
                card.setCreate_date(resultSet.getTimestamp("create_date").toLocalDateTime());
                card.setExpDate(resultSet.getString("exp_date"));
                card.setStatus(CardStatus.valueOf(resultSet.getString("status")));
                return card;
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

    public void deleteCard(String  number){
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete  from card where number = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,number);
            ps.executeUpdate();
            System.out.println("Succes");
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

    public void updateCard(String card_number, String status){
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update card set status = ? where number = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, card_number);
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

    public void updateCardNumber(String old_number, String new_number) {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update card set number = ? where number = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, new_number);
            ps.setString(2, old_number);
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

    public void update(String card_number, Double balance) {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update card set balance = ? where number = ?";
            ps = connection.prepareStatement(sql);
            ps.setDouble(1, balance);
            ps.setString(2, card_number);
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

    public void createProfileCard(ProfileCard profileCard) {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = """
                        insert into profil_card(profil_phone,card_number,create_date,status)
                        values (?,?,?,?)
                        """;
            ps = connection.prepareStatement(sql);
            ps.setString(1,profileCard.getPhoneNum());
            ps.setString(2, profileCard.getCardNum());
            ps.setTimestamp(3,Timestamp.valueOf(profileCard.getCreateDate()));
            ps.setString(4,profileCard.getStatus().name());
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

    public ProfileCard getProfileCard(String cardNum) {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select * from profil_card where card_number = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,cardNum);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
               ProfileCard profileCard = new ProfileCard();
                profileCard.setCardNum(resultSet.getString("card_number"));
                profileCard.setPhoneNum(resultSet.getString("profil_phone"));
                return profileCard;
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

    public void changeTypeProfileCard(String cardNum, String type) {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update profil_card set type = ? where card_number = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, type);
            ps.setString(2, cardNum);
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

    public void changeStatusProfilCard(String cardNum) {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update profil_card set status = 'UNVISIABLE' where card_number = ?";
            ps = connection.prepareStatement(sql);

            ps.setString(1, cardNum);
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
        CardRepository.dbConnection = dbConnection;
    }


}
