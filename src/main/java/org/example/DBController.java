package org.example;
import org.example.Model.Customer;
import org.example.Model.TransactionAudit;

import java.sql.*;
import java.sql.DriverManager;

public class DBController {


    DriverManagerDB drivermanagerdb = new DriverManagerDB();

    Connection connection = drivermanagerdb.connect();

    public DBController() throws SQLException {
    }

/*
    public void connectToDb(){

        // 2) Get a connection (driver auto-loads if the JAR is on the class-path)
        try (Connection connection = java.sql.DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement("SELECT version()");
             ResultSet rs = ps.executeQuery()) {

            // 3) Work with the DB
            if (rs.next()) {
                System.out.println("Connected! Server says: " + rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();               // TODO: real error handling
        }
    }
*/

    public void addCustomerRecord(String name, int money, String password) throws SQLException {
        String sql = "INSERT INTO customers (nickname, money, password) " + "VALUES (?, ?, ?)";

                       PreparedStatement ps = connection.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);{

            ps.setString(1, name);
            ps.setInt(2, money);
            ps.setString(3, password);

            int rows = ps.executeUpdate();                          // returns 1
            System.out.println("Rows inserted: " + rows);

            // --- fetch the auto-generated primary key -------------
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    long id = keys.getLong(1);                      // ← your SERIAL / IDENTITY value
                    System.out.println("New customer id = " + id);
                }
            }
    }
    }

    public Customer returnRecord(int customerId) throws SQLException {

        int id = customerId;
        String name = null;
        int money = 0;
        String cusPassword = null;
        String sql = "SELECT id, nickname, money, password FROM customers WHERE id = ?";
        // + customerId + " (id, nickname, money, password) " + "VALUES (?, ?, ?)";

        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    id = rs.getInt("id");           // error here probably
                    name = rs.getString("nickname");
                    money = rs.getInt("money");
                    cusPassword = rs.getString("password");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Customer(id, name, money, cusPassword);
    }


    public int findCustomerByUsername(String name){
        int id = 0;
        String sql = "SELECT id FROM customers WHERE nickname = ?";
        // + customerId + " (id, nickname, money, password) " + "VALUES (?, ?, ?)";
        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    id = rs.getInt("id");
                    return id;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
//  else {
//        System.out.println("No row found for nickname = " + name);
//    }

    public boolean updateCustomerFunds(int customerId, int newAmount) {  //void

        String sql = "UPDATE customers SET money = ? WHERE id = ? ";
        int updated = 0;
        try ( //
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, newAmount);
            ps.setInt(2,customerId);

            updated = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return true;
        }
        return false;
    }

    public void createTransactionAudit(int senderId, String senderName, int moneyInCents, int getterId, String getterName) throws SQLException {
        String sql = "INSERT INTO transactions_audit (sender_id, sender_nickname, amount_sent, receiver_id, receiver_nickname) " + "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        {

            ps.setInt(1, senderId);
            ps.setString(2, senderName);
            ps.setInt(3, moneyInCents);
            ps.setInt(4, getterId);
            ps.setString(5, getterName);

            int rows = ps.executeUpdate();                          // returns 1
            System.out.println("Rows inserted: " + rows);
        }
    }

    public TransactionAudit returnTransactionAudit(int transactionid) throws SQLException {

        int id = transactionid;
        int sender_id = 0;
        String sender_nickname = null;
        int amount_sent = 0;
        int receiver_id = 0;
        String receiver_nickname = null;
        String time = null;

        String sql = "SELECT id, sender_id, sender_nickname, amount_sent, receiver_id, receiver_nickname FROM transactions_audit WHERE id = ?";
        // + customerId + " (id, nickname, money, password) " + "VALUES (?, ?, ?)";

        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    id = rs.getInt("id");
                    sender_id = rs.getInt("sender_id");
                    sender_nickname = rs.getString("sender_nickname");
                    amount_sent = rs.getInt("amount_sent");
                    receiver_id = rs.getInt("receiver_id");
                    receiver_nickname = rs.getString("receiver_nickname");
                    time = rs.getString("time");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new TransactionAudit(id, sender_id, sender_nickname, amount_sent, receiver_id, receiver_nickname, time);
    }

    public void insertIntoFriendships(int senderId, int receiverId) throws SQLException {
        String sql = "INSERT INTO friendships (customer_id, friend_id) VALUES (?, ?)";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, senderId);    // customer_id
        ps.setLong(2, receiverId);     // friend_id
        ps.executeUpdate();
    }

    public boolean checkIfHasFriend(int senderId, int receiverId) throws SQLException {
        String sql = "SELECT FROM friendships WHERE customer_id = ? AND friend_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setLong(1, senderId);
        ps.setLong(2, receiverId);

        ResultSet rs = ps.executeQuery();
        boolean result = rs.next();

        if (!result) {
            ps.setLong(1, receiverId);
            ps.setLong(2, senderId);

            rs = ps.executeQuery();
            return rs.next();
        }
        return true;
    }

    public void insertIntoTransfers(int senderId, int receiverId, int amount) throws SQLException {

        PreparedStatement addTx = connection.prepareStatement("INSERT INTO transfers (sender_id, receiver_id, amount_cents) VALUES (?,?,?)");
        addTx.setLong(1, senderId);
        addTx.setLong(2, receiverId);
        addTx.setLong(3, amount);
        addTx.executeUpdate();
    }

    public void insertIntoTransferTotals1(int senderId, int receiverId, int amount) throws SQLException {
        PreparedStatement upsert1 = connection.prepareStatement(
                """
                INSERT INTO transfer_totals (customer_id, friend_id, sent_cents)
                VALUES (?, ?, ?)
                ON CONFLICT (customer_id, friend_id)
                DO UPDATE SET sent_cents = transfer_totals.sent_cents + EXCLUDED.sent_cents
                """);
            upsert1.setLong(1, senderId);
            upsert1.setLong(2, receiverId);
            upsert1.setLong(3, amount);
            upsert1.executeUpdate();
    }

    public void insertIntoTransferTotals2(int senderId, int receiverId, int amount) throws SQLException {
        PreparedStatement upsert2 = connection.prepareStatement(
                """
                        INSERT INTO transfer_totals (customer_id, friend_id, received_cents)
                        VALUES (?, ?, ?)
                        ON CONFLICT (customer_id, friend_id)
                        DO UPDATE SET received_cents = transfer_totals.received_cents + EXCLUDED.received_cents
                        """);
        upsert2.setLong(1, receiverId);
        upsert2.setLong(2, senderId);
        upsert2.setLong(3, amount);
        upsert2.executeUpdate();
    }

        public void createTable() throws SQLException {
        String sql = """ 
        CREATE TABLE IF NOT EXISTS test (
        id  BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        friend_name TEXT NOT NULL,
        friend_status TEXT NOT NULL,
        total_money_sent BIGINT NOT NULL,
        total_money_received BIGINT NOT NULL,
        since_when TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
        );
        """;

        Statement st = connection.createStatement();
            st.executeUpdate(sql);

    }

}


