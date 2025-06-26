package com.example.send_go.Dao.Provincia;

import java.sql.*;

public class SendGoDaoProvinciaImpl implements SendGoDaoProvincia{
    private Connection conn;
    public SendGoDaoProvinciaImpl(String ip, String port, String dbName, String userName, String pwd){
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

    public boolean provExist(String provincia) {
        String query = "SELECT 1 FROM provincia WHERE nome = ? LIMIT 1";

        try {
             PreparedStatement stmt = conn.prepareStatement(query);

                stmt.setString(1, provincia);
                ResultSet rs = stmt.executeQuery();
                return rs.next();
            }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
