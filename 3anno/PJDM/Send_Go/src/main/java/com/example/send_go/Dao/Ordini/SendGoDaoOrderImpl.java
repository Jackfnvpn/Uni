package com.example.send_go.Dao.Ordini;

import com.example.send_go.Entity.Indirizzo.Indirizzo;
import com.example.send_go.Entity.Ordine.ListOrder;
import com.example.send_go.Entity.Ordine.Ordine;
import com.example.send_go.Entity.Spedizione.Spedizione;
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
    public int insertSpedizioni(Spedizione s) {
        String sql = """
            INSERT INTO spedizione (
                Nome_Partenza, Cognome_Partenza,
                Nome_Destinazione, Cognome_Destinazione,
                data_ritiro,
                Cap_Mittenza, Località_Mittenza, Provincia_Mittenza, Indirizzo_Mittenza,
                Cap_Destinazione, Località_Destinazione, Provincia_Destinazione, Indirizzo_Destinazione,
                Peso, Altezza, Lunghezza, Larghezza,
                N_ordine, N_corriere, tariffa_applicata
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)
         """;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, s.getPartenza().getNome());
            ps.setString(2, s.getPartenza().getCognome());

            ps.setString(3, s.getDestinazione().getNome());
            ps.setString(4, s.getDestinazione().getCognome());

            ps.setDate(5, Date.valueOf(s.getDataRitiro()));


            ps.setString(6, s.getPartenza().getCap());
            ps.setString(7, s.getPartenza().getCitta());
            ps.setString(8, s.getPartenza().getProvincia());
            ps.setString(9, s.getPartenza().getIndirizzo());

            ps.setString(10, s.getDestinazione().getCap());
            ps.setString(11, s.getDestinazione().getCitta());
            ps.setString(12, s.getDestinazione().getProvincia());
            ps.setString(13, s.getDestinazione().getIndirizzo());

            ps.setDouble(14, s.getDimensioniPacco().getPeso());
            ps.setDouble(15, s.getDimensioniPacco().getAltezza());
            ps.setDouble(16, s.getDimensioniPacco().getLunghezza());
            ps.setDouble(17, s.getDimensioniPacco().getLarghezza());

            ps.setInt(18, s.getIdOrdine());
            ps.setInt(19, s.getCorriere().getIdCorriere());
            ps.setDouble(20, s.getCorriere().getCorriere_tariffa());


            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int clearCart(int idCliente) {
        int result = 0;
        String query = "DELETE FROM voci_carrello WHERE N_cliente = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idCliente);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return result;
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

    public boolean creaOrdineCompleto(Ordine ordine, ArrayList<Spedizione> spedizioni, int idCliente) {
        try {
            conn.setAutoCommit(false);

            int idOrdine = insertOrdine(ordine, idCliente);
            if (idOrdine <= 0) throw new SQLException("Errore inserimento ordine");

            for (Spedizione s : spedizioni) {
                s.setIdOrdine(idOrdine);
                int res = insertSpedizioni(s);
                if (res <= 0) throw new SQLException("Errore inserimento spedizione");
            }

            int clearRes = clearCart(idCliente);
            if (clearRes < 0) throw new SQLException("Errore svuotamento carrello");

            conn.commit();
            return true;

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
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
