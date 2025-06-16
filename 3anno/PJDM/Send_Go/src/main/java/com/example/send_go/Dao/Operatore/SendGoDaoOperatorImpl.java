package com.example.send_go.Dao.Operatore;
import com.example.send_go.Entity.Customer;

import java.sql.*;
import java.util.Enumeration;

public class SendGoDaoOperatorImpl implements SendGoDaoOperator{
    private Connection conn;

    public SendGoDaoOperatorImpl(String ip, String port, String dbName, String userName, String pwd){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbName
                            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    userName, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer checkCustomer(String email, String password) {
        String sql = "SELECT * FROM operatore WHERE email = ? AND password = ?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Customer customer = new Customer(
                            rs.getString("Nome"),
                            rs.getString("Cognome"),
                            rs.getString("Telefono"),
                            rs.getString("Email"),
                            rs.getString("Password")
                    );

                    customer.setId(rs.getInt("N_operatore"));

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
        String sql = "INSERT INTO operatore(Nome,Cognome,Numero_Telefono,Email,Password) VALUES(?,?,?,?,?)";

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

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Enumeration<Driver> enumDrivers = DriverManager.getDrivers();
            while (enumDrivers.hasMoreElements()) {
                Driver driver = enumDrivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
