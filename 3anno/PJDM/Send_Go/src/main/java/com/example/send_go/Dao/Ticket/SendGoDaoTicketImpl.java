package com.example.send_go.Dao.Ticket;

import com.example.send_go.Entity.Customer;
import com.example.send_go.Entity.Messaggio.Messaggio;
import com.example.send_go.Entity.Ordine.Ordine;
import com.example.send_go.Entity.Spedizione.ListSpedizione;
import com.example.send_go.Entity.Spedizione.Spedizione;
import com.example.send_go.Entity.Ticket.ListTicket;
import com.example.send_go.Entity.Ticket.Ticket;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;

public class SendGoDaoTicketImpl implements SendGoDaoTicket {
    private Connection conn;

    public SendGoDaoTicketImpl(String ip, String port, String dbName, String userName, String pwd){
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


    @Override
    public Ticket getTicket(int ticketId){
        Ticket ticket = null;
        ArrayList<Messaggio> messaggi = new ArrayList<>();
        int ordineId;
        int spedizioneId;

        String sql = "SELECT t.*,m.N_messaggio,m.testo,m.Data,m.mittente_tipo FROM ticket t JOIN messaggio m ON t.N_ticket=m.N_ticket  WHERE t.N_ticket = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ticketId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (ticket == null) {
                    ordineId = rs.getInt("N_ordine");
                    spedizioneId = rs.getInt("N_spedizione");
                    ticket = new Ticket(spedizioneId, ordineId);
                    ticket.setId(ticketId);
                }

                String testo = rs.getString("testo");
                Date data = rs.getDate("Data");
                LocalDate new_data = data.toLocalDate();
                int mittenteTipo = rs.getInt("mittente_tipo");
                Messaggio msg = new Messaggio(testo, new_data, mittenteTipo);
                msg.setIdMessaggio(rs.getInt("N_messaggio"));
                messaggi.add(msg);
            }

            rs.close();
            stmt.close();

            if (ticket != null) {
                ticket.setMessaggio(messaggi);
            }
            return ticket;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public ListTicket getListTicket(int idCliente) {
        String sql =
                """
                SELECT t.*
                FROM ticket t 
                JOIN ordine o ON t.N_ordine = o.N_ordine 
                WHERE o.N_cliente = ?
                """ ;

        ListTicket list = new ListTicket();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int ticketId = rs.getInt("N_ticket");
                int ordineId = rs.getInt("N_ordine");
                int spedizioneId = rs.getInt("N_spedizione");

                Ticket ticket = new Ticket(spedizioneId, ordineId);
                ticket.setId(ticketId);
                list.add(ticket);
            }
            
            rs.close();
            stmt.close();
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public ListTicket getListAllTicket() {
        String sql =
                """
                SELECT *
                FROM ticket 
                """ ;

        ListTicket list = new ListTicket();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int ticketId = rs.getInt("N_ticket");
                int ordineId = rs.getInt("N_ordine");
                int spedizioneId = rs.getInt("N_spedizione");

                Ticket ticket = new Ticket(spedizioneId, ordineId);
                ticket.setId(ticketId);
                list.add(ticket);
            }

            rs.close();
            stmt.close();
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public int insertTicket(Ticket ticket) {
        String sql = "INSERT INTO ticket(N_spedizione,N_ordine) VALUES(?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, ticket.getSpedizioneId());
            pstmt.setInt(2, ticket.getOrdineId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    ticket.setId(generatedId);
                }
                return ticket.getId_ticket();
            }
            return -1;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
