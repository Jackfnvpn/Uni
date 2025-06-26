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
            System.out.println("Connected to database");

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

    public int insertCorriere(String nome) {
        String sql = "INSERT INTO corriere (Nome) VALUES (?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nome);

            int affectedRows = pstmt.executeUpdate();

            return affectedRows;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public ListCorriere getCorrieres() {
        String sql = "SELECT * FROM corriere ORDER BY Nome";

        try {
            PreparedStatement  pstmt = conn.prepareStatement(sql);
            ResultSet rset = pstmt.executeQuery();
            ListCorriere res = new ListCorriere();

            while (rset.next()) {
                int id = rset.getInt("ID");
                String nome = rset.getString("Nome");
                Corriere corriere = new Corriere(id,nome);
                res.add(corriere);
            }
            rset.close();
            pstmt.close();
            return res;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }



        public ListCorriere getCorrieres(double pesoTassabile){
        String sql = """
                        SELECT c.*, t.valore_tariffa AS Valore_Tariffa 
                        FROM corriere c 
                        JOIN tariffa t ON t.corriere_id = c.ID
                        WHERE t.peso = ?
                        ORDER BY Valore_Tariffa ASC
                    """;

        try {
            PreparedStatement  pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, pesoTassabile);
            ResultSet rset = pstmt.executeQuery();
            ListCorriere res = new ListCorriere();

            while (rset.next()) {
                int id = rset.getInt("ID");
                String nome = rset.getString("Nome");
                double tariffa = rset.getDouble("Valore_Tariffa");

                Corriere corriere = new Corriere(id,nome, tariffa);
                res.add(corriere);
            }

            rset.close();
            pstmt.close();
            return res;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
