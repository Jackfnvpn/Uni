package com.example.send_go.Dao.VociCarrello;

import com.example.send_go.Entity.Carrello.ListCarello;
import com.example.send_go.Entity.Carrello.VociCarrello;
import com.example.send_go.Entity.Corriere.Corriere;
import com.example.send_go.Entity.Corriere.ListCorriere;
import com.example.send_go.Entity.Indirizzo.Indirizzo;
import com.example.send_go.Entity.Pacco.Pacco;
import com.example.send_go.Entity.Ticket.ListTicket;
import com.example.send_go.Entity.Ticket.Ticket;

import java.sql.*;
import java.time.LocalDate;

public class SendGoDaoVociCartImpl implements SendGoDaoVociCart {
    private Connection conn;

    public SendGoDaoVociCartImpl(String ip, String port, String dbName, String userName, String pwd){
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
    public ListCarello getListCarrello(int idCliente) {
        String sql = "SELECT vc.*, c.nome AS nome_corriere FROM voci_carrello vc JOIN corriere c ON vc.N_corriere=c.ID WHERE vc.N_cliente = ?";
        ListCarello list = new ListCarello();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                double peso = rs.getDouble("Peso");
                double altezza = rs.getDouble("Altezza");
                double lunghezza = rs.getDouble("Lunghezza");
                double larghezza = rs.getDouble("Larghezza");
                Pacco pacco = new Pacco(lunghezza, larghezza, altezza, peso);

                int idCorriere = rs.getInt("N_corriere");
                String nome = rs.getString("nome_corriere");
                double tariffa = rs.getDouble("tariffa_applicata");
                Corriere corriere = new Corriere(idCorriere,nome,tariffa);

                LocalDate dataRitiro = rs.getDate("data_ritiro").toLocalDate();
                int id = rs.getInt("ID");
                Indirizzo partenza = new Indirizzo(
                        rs.getString("Nome_Partenza"),
                        rs.getString("Cognome_Partenza"),
                        rs.getString("Indirizzo_Mittenza"),
                        rs.getString("Località_Mittenza"),
                        rs.getString("Cap_Mittenza"),
                        rs.getString("Provincia_Mittenza")
                );

                Indirizzo destinazione = new Indirizzo(
                        rs.getString("Nome_Destinazione"),
                        rs.getString("Cognome_Destinazione"),
                        rs.getString("Indirizzo_Destinazione"),
                        rs.getString("Località_Destinazione"),
                        rs.getString("Cap_Destinazione"),
                        rs.getString("Provincia_Destinazione")
                );

                VociCarrello voce = new VociCarrello(pacco, corriere, dataRitiro, partenza, destinazione,id);
                list.add(voce);
            }
            rs.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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


    @Override
    public int insertVociCart(VociCarrello vociCarrello, int idCliente) {
        String sql = """
                INSERT INTO voci_carrello (
                N_cliente, Nome_Partenza, Cognome_Partenza, Nome_Destinazione, Cognome_Destinazione,
                data_ritiro, Cap_Mittenza, Località_Mittenza, Provincia_Mittenza, Indirizzo_Mittenza,
                Cap_Destinazione, Località_Destinazione,Provincia_Destinazione, Indirizzo_Destinazione,
                Peso, Altezza, Lunghezza, Larghezza, N_corriere,tariffa_applicata)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)
            """;

        try {
            var stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.setString(2, vociCarrello.getPartenza().getNome());
            stmt.setString(3, vociCarrello.getPartenza().getCognome());

            stmt.setString(4, vociCarrello.getDestinazione().getNome());
            stmt.setString(5, vociCarrello.getDestinazione().getCognome());

            stmt.setDate(6, java.sql.Date.valueOf(vociCarrello.getDataRitiro()));

            stmt.setString(7, vociCarrello.getPartenza().getCap());
            stmt.setString(8, vociCarrello.getPartenza().getCitta());
            stmt.setString(9, vociCarrello.getPartenza().getProvincia());
            stmt.setString(10, vociCarrello.getPartenza().getIndirizzo());

            stmt.setString(11, vociCarrello.getDestinazione().getCap());
            stmt.setString(12, vociCarrello.getDestinazione().getCitta());
            stmt.setString(13, vociCarrello.getDestinazione().getProvincia());
            stmt.setString(14, vociCarrello.getDestinazione().getIndirizzo());

            stmt.setDouble(15, vociCarrello.getDimensioniPacco().getPeso());
            stmt.setDouble(16, vociCarrello.getDimensioniPacco().getAltezza());
            stmt.setDouble(17, vociCarrello.getDimensioniPacco().getLunghezza());
            stmt.setDouble(18, vociCarrello.getDimensioniPacco().getLarghezza());

            stmt.setInt(19, vociCarrello.getCorriere().getIdCorriere());
            stmt.setDouble(20, vociCarrello.getCorriere().getCorriere_tariffa());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean deleteVoce(int id){
        String sql = "DELETE FROM voci_carrello WHERE ID = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            ps.close();

            if (rows > 0) {
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}
