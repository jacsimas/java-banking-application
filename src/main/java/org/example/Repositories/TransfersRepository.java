package org.example.Repositories;
import org.example.DriverManagerDB;
import org.example.Model.TransactionAudit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class TransfersRepository {


    DriverManagerDB drivermanagerdb = new DriverManagerDB();

    Connection connection = drivermanagerdb.connect();

    final Logger logger = LoggerFactory.getLogger(TransfersRepository.class);

    public TransfersRepository() throws SQLException {
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

    public void createTransactionAudit(int senderId, String senderName, int moneyInCents, int getterId, String getterName) throws SQLException {
        String sql = "INSERT INTO transactions_audit (sender_id, sender_nickname, amount_sent, receiver_id, receiver_nickname) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS);
        {
            ps.setInt(1, senderId);
            ps.setString(2, senderName);
            ps.setInt(3, moneyInCents);
            ps.setInt(4, getterId);
            ps.setString(5, getterName);

            int rowsInserted = ps.executeUpdate();                          // returns 1
            logger.info("Number of rows inserted: {}", rowsInserted);
        }
    }

    /*

     */
    public TransactionAudit returnTransactionAudit(int transactionid) throws SQLException {

        int id = transactionid;
        int sender_id = 0;
        String sender_nickname = null;
        int amount_sent = 0;
        int receiver_id = 0;
        String receiver_nickname = null;
        String time = null;

        String sql = "SELECT id, sender_id, sender_nickname, amount_sent, receiver_id, receiver_nickname FROM transactions_audit WHERE id = ?";

        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);

           ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    id = rs.getInt("id");
                    sender_id = rs.getInt("sender_id");
                    sender_nickname = rs.getString("sender_nickname");
                    amount_sent = rs.getInt("amount_sent");
                    receiver_id = rs.getInt("receiver_id");
                    receiver_nickname = rs.getString("receiver_nickname");
                    time = rs.getString("time");
                }
            //TODO: same problem, try without catch was looped in try catch
        } catch (SQLException e) {
            logger.info("{} ", e);

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

    public void insertIntoTransfers(int senderId, int receiverId, int amount) throws SQLException {

        PreparedStatement addTx = connection.prepareStatement("INSERT INTO transfers (sender_id, receiver_id, amount_cents) VALUES (?,?,?)");
        addTx.setLong(1, senderId);
        addTx.setLong(2, receiverId);
        addTx.setLong(3, amount);
        addTx.executeUpdate();
    }

    public void insertSenderReceiverIntoTransferTotals(int senderId, int receiverId, int amount) throws SQLException {
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

    public void insertReceiverSenderIntoTransferTotals(int senderId, int receiverId, int amount) throws SQLException {
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


// TODO: split this class to atleast two different repositories