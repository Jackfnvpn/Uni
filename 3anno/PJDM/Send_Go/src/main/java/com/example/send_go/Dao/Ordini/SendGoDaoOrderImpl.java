package com.example.send_go.Dao.Ordini;

import com.example.send_go.Entity.Indirizzo.Indirizzo;
import com.example.send_go.Entity.Ordine.ListOrder;
import com.example.send_go.Entity.Ordine.Ordine;
import com.example.send_go.Entity.Ticket.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


public class SendGoDaoOrderImpl implements SendGoDaoOrder{
    private Connection conn;

    public SendGoDaoOrderImpl(String ip, String port, String dbName, String userName, String pwd){
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbName
                            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    userName, pwd);
        }
        catch (Exception e) {
            e.printStackTrace();
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

    public ListOrder getListOrder(int idCliente){
        String sql = "SELECT * FROM ordine WHERE N_cliente = ?";
        ListOrder listOrder = new ListOrder();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ordine order = new Ordine(rs.getInt("N_ordine"),rs.getDouble("Prezzo"));
                listOrder.add(order);
            }
            rs.close();
            stmt.close();
            return listOrder;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

   public int insertOrdine(Ordine ordine,int idCliente){
        String sql = "INSERT INTO ordine(Prezzo,N_cliente) VALUES (?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, String.valueOf(ordine.getPrice()));
            pstmt.setString(2, String.valueOf(ordine.getCliente()));
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    ordine.setId(generatedId);
                }
                return ordine.getId();
            } else {
                return -1;
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }
}
