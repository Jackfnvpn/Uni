package com.example.send_go.Dao.Mesaggio;

import com.example.send_go.Entity.Messaggio.Messaggio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SendGoDaoMessaggioImpl implements SendGoDaoMessaggio{
    private Connection conn;
    public SendGoDaoMessaggioImpl(String ip, String port, String dbName, String userName, String pwd){
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbName
                            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    userName, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int insertMessaggio(Messaggio msg, int ticketId) {
        String sql = "INSERT INTO messaggio(N_ticket, testo, Data, mittente_tipo) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ticketId);
            stmt.setString(2, msg.getMessaggio());
            stmt.setDate(3, java.sql.Date.valueOf(msg.getDataMessaggio()));
            stmt.setInt(4, msg.getFlagMittente());
            int affectedRow = stmt.executeUpdate();

            if (affectedRow > 0) {
                return affectedRow;
            }
            else{
                return -1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
