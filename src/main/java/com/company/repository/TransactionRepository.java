package com.company.repository;

import com.company.db.DbConnection;
import com.company.dto.ProfileCard;
import com.company.dto.TransactionDT0;
import com.company.enums.ProfileCardType;
import com.company.enums.TransactionType;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class TransactionRepository {
    DbConnection dbConnection = null;
    public void createTransaction(TransactionDT0 transaction) {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into transaction_table (card_num,terminal_cod,create_date,amaunt,type) " +
                    "values(?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, transaction.getCard_num());
            ps.setString(2, transaction.getTerminal_cod());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setDouble(4, transaction.getAmaunt());
            ps.setString(5, transaction.getType().name());
            ps.executeUpdate();
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

    public List<TransactionDT0> list() {
        List<TransactionDT0> list = new LinkedList<>();
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select *  from transaction_table";
            ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                TransactionDT0 transactionDT0 = new TransactionDT0();
                transactionDT0.setId(resultSet.getInt("id"));
                transactionDT0.setType(TransactionType.valueOf(resultSet.getString("type")));
                transactionDT0.setCreate_date(resultSet.getTimestamp("create_date").toLocalDateTime());
                transactionDT0.setTerminal_cod(resultSet.getString("terminal_cod"));
                transactionDT0.setCard_num(resultSet.getString("card_num"));
                transactionDT0.setAmaunt(resultSet.getLong("amaunt"));
                list.add(transactionDT0);

            }
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
            return list;
        }
    }

    public List<TransactionDT0> getTranToDate(LocalDate toDate) {
        List<TransactionDT0> list = new LinkedList<>();
        Connection connection =dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select id,type,terminal_cod,card_num,amaunt," +
                    "create_date from transaction_table where  date(create_date) =?";
            ps = connection.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(toDate));
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                TransactionDT0 transactionDT0 = new TransactionDT0();
                transactionDT0.setId(resultSet.getInt("id"));
                transactionDT0.setType(TransactionType.valueOf(resultSet.getString("type")));
                transactionDT0.setCreate_date(resultSet.getTimestamp("create_date").toLocalDateTime());
                transactionDT0.setTerminal_cod(resultSet.getString("terminal_cod"));
                transactionDT0.setCard_num(resultSet.getString("card_num"));
                transactionDT0.setAmaunt(resultSet.getLong("amaunt"));
                list.add(transactionDT0);

            }
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
            return list;
        }
    }

    public List<TransactionDT0> getTranDate(LocalDate toDate, LocalDate fromDate) {
        List<TransactionDT0> list = new LinkedList<>();
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = """ 
                    select *
                    from transaction_table
                    where date(create_date) between ? and ?
                    """;
            ps = connection.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(toDate));
            ps.setDate(2, Date.valueOf(fromDate));
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                TransactionDT0 transactionDT0 = new TransactionDT0();
                transactionDT0.setId(resultSet.getInt("id"));
                transactionDT0.setType(TransactionType.valueOf(resultSet.getString("type")));
                transactionDT0.setCreate_date(resultSet.getTimestamp("create_date").toLocalDateTime());
                transactionDT0.setTerminal_cod(resultSet.getString("terminal_cod"));
                transactionDT0.setCard_num(resultSet.getString("card_num"));
                transactionDT0.setAmaunt(resultSet.getLong("amaunt"));
                list.add(transactionDT0);

            }
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
            return list;
        }
    }

    public List<TransactionDT0> getTranByCard(String card) {
        List<TransactionDT0> list = new LinkedList<>();
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = """
                        select * from transaction_table
                        where card_num = ? 
                        """;
            ps = connection.prepareStatement(sql);
            ps.setString(1, card);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                TransactionDT0 transactionDT0 = new TransactionDT0();
                transactionDT0.setId(resultSet.getInt("id"));
                transactionDT0.setType(TransactionType.valueOf(resultSet.getString("type")));
                transactionDT0.setCreate_date(resultSet.getTimestamp("create_date").toLocalDateTime());
                transactionDT0.setTerminal_cod(resultSet.getString("terminal_cod"));
                transactionDT0.setCard_num(resultSet.getString("card_num"));
                transactionDT0.setAmaunt(resultSet.getLong("amaunt"));
                list.add(transactionDT0);

            }
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
            return list;

        }
    }

    public List<ProfileCard> getTranByPhone(String phone) {
        List<ProfileCard> list = new LinkedList<>();
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = """
                        select card_number,profil_phone,
                        balance,exp_date,profil_card.type  from profil_card
                        inner join card on card_number = number
                        where profil_phone = ? 
                        """;
            ps = connection.prepareStatement(sql);
            ps.setString(1, phone);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                ProfileCard profileCard = new ProfileCard();
                profileCard.setCardNum(resultSet.getString("card_number"));
                profileCard.setPhoneNum(resultSet.getString("profil_phone"));
                profileCard.setBalance(resultSet.getLong("balance"));
                profileCard.setExpDate(resultSet.getString("exp_date"));
                profileCard.setType(ProfileCardType.valueOf(resultSet.getString("type")));
                list.add(profileCard);

            }
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
            return list;
        }
        }

    public List<TransactionDT0> getTranUser(String phone) {
        List<TransactionDT0> list = new LinkedList<>();
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = """
                       select card_number,terminal_cod,transaction_table.create_date,
                       transaction_table.type,amaunt
                       from transaction_table
                       join profil_card pc on transaction_table.card_num = pc.card_number
                       where  profil_phone = ?
                       order by create_date desc
                        """;
            ps = connection.prepareStatement(sql);
            ps.setString(1, phone);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                TransactionDT0 profileCard = new TransactionDT0();
                profileCard.setCard_num(resultSet.getString("card_number"));
                profileCard.setTerminal_cod(resultSet.getString("terminal_cod"));
                profileCard.setType(TransactionType.valueOf(resultSet.getString("type")));
                profileCard.setAmaunt(resultSet.getLong("amaunt"));
                profileCard.setCreate_date(resultSet.getTimestamp("create_date").toLocalDateTime());
                if(profileCard.getType().equals(TransactionType.PAYMENT)){
                    list.add(profileCard);
                }

            }
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
            return list;
        }
    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
}
