package com.example.send_go.Dao.Corriere;

import com.example.send_go.Entity.Corriere.Corriere;
import com.example.send_go.Entity.Corriere.ListCorriere;

import java.sql.*;

public class SendGoDaoCorriereImpl implements SendGoDaoCorriere {
    private Connection conn;

    public SendGoDaoCorriereImpl(String ip, String port, String dbName, String userName, String pwd){
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbName
                            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    userName, pwd);

        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore generico", e);

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

    public ListCorriere getCorrieres(){
        String sql = "SELECT * FROM corriere ORDER BY Tariffa ASC";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(sql);
            ListCorriere res = new ListCorriere();

            while (rset.next()) {
                int id = rset.getInt("ID");
                String nome = rset.getString(2);
                double tariffa = rset.getDouble(3);

                Corriere corriere = new Corriere(id,nome, tariffa);
                res.add(corriere);
            }
            rset.close();
            stmt.close();
            return res;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
