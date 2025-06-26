package com.example.send_go.Dao.Tariffa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SendGoDaoTariffa implements SendGoDaoTariffaInt{
    private Connection conn;
    public SendGoDaoTariffa(String ip, String port, String dbName, String userName, String pwd){
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbName
                            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    userName, pwd);
            System.out.println("Connected to database");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int updateTariffa(int idCorr,double tariffa, double peso){
        String sql = "UPDATE tariffa SET valore_tariffa = ? WHERE peso = ? AND corriere_id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setDouble(1, tariffa);
                stmt.setDouble(2, peso);
                stmt.setInt(3, idCorr);

            int rowsUpdated = stmt.executeUpdate();
                stmt.close();
                return rowsUpdated;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
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

    @Override
    public boolean isConnected() {
        try {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int insertTariffa(int idCorriere, double peso, double tariffa){
        String sql = "INSERT INTO tariffa (corriere_id, peso , valore_tariffa) VALUES (?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idCorriere);
            pstmt.setDouble(2, peso);
            pstmt.setDouble(3, tariffa);

            int rows = pstmt.executeUpdate();
            pstmt.close();
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }



}
