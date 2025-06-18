package com.example.send_go.Dao.Customer;

import com.example.send_go.Entity.Customer;

import java.sql.*;
import java.util.Enumeration;

public class SendGoDaoCustomerImpl implements SendGoDaoCustomer {
    private Connection conn;

    public SendGoDaoCustomerImpl(String ip, String port, String dbName, String userName, String pwd) throws Exception {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbName
                            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    userName, pwd);
        } catch (Exception e) {
            throw new Exception("Errore nella connessione al database", e);
        }
    }

    @Override
    public Customer checkCustomer(String email, String password) {
        String sql = "SELECT * FROM customer WHERE email = ? AND password = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Customer customer = new Customer(
                            rs.getString("nome"),
                            rs.getString("cognome"),
                            rs.getString("numero_telefono"),
                            rs.getString("email"),
                            rs.getString("password")
                    );

                    customer.setId(rs.getInt("N_cliente"));

                    return customer;
                } else {
                    return null; //
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public int insertCustomer(Customer customer) {
        String sql = "INSERT INTO customer(Nome,Cognome,Numero_Telefono,Email,Password) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, customer.getNome());
            pstmt.setString(2, customer.getCognome());
            pstmt.setString(3, customer.getNumeroTelefono());
            pstmt.setString(4,customer.getEmail());
            pstmt.setString(5, customer.getPassword());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    customer.setId(generatedId);
                }
                return affectedRows;
            } else {
                return -1;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean isConnected() {
        try {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void closeConnection() {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
