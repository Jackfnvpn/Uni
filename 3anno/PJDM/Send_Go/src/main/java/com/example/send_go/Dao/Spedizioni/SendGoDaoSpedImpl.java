package com.example.send_go.Dao.Spedizioni;

import com.example.send_go.Entity.Corriere.Corriere;
import com.example.send_go.Entity.Indirizzo.Indirizzo;
import com.example.send_go.Entity.Pacco.Pacco;
import com.example.send_go.Entity.Spedizione.ListSpedizione;
import com.example.send_go.Entity.Spedizione.Spedizione;
import com.example.send_go.Entity.Spedizione.listCoppieId;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SendGoDaoSpedImpl implements SendGoDaoSped {

    private Connection conn;

    public SendGoDaoSpedImpl(String ip, String port, String dbName, String userName, String pwd) {
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
    public ListSpedizione getListSpedizioni(int idCliente){
        ListSpedizione lista = new ListSpedizione();
        String sql =
                """
                    SELECT s.*,c.Nome AS Nome_corriere, c.Tariffa AS Tariffa_corriere 
                    FROM spedizione s 
                    JOIN ordine o ON o.N_ordine=s.N_ordine 
                    JOIN corriere c ON s.N_corriere=c.ID
                    WHERE o.N_cliente=?    
                """;

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Indirizzo partenza = new Indirizzo(
                        rs.getString("Nome_Partenza"),
                        rs.getString("Cognome_Partenza"),
                        rs.getString("Cap_Mittenza"),
                        rs.getString("Località_Mittenza"),
                        rs.getString("Provincia_Mittenza"),
                        rs.getString("Indirizzo_Mittenza")
                );

                Indirizzo destinazione = new Indirizzo(
                        rs.getString("Nome_Destinazione"),
                        rs.getString("Cognome_Destinazione"),
                        rs.getString("Cap_Destinazione"),
                        rs.getString("Località_Destinazione"),
                        rs.getString("Provincia_Destinazione"),
                        rs.getString("Indirizzo_Destinazione")
                );

                Pacco pacco = new Pacco(
                        rs.getDouble("Peso"),
                        rs.getDouble("Altezza"),
                        rs.getDouble("Lunghezza"),
                        rs.getDouble("Larghezza")
                );

                Corriere corriere = new Corriere(
                        rs.getInt("N_corriere"),
                        rs.getString("Nome_corriere"),
                        rs.getDouble("Tariffa_corriere")
                );

                LocalDate dataRitiro = rs.getDate("data_ritiro").toLocalDate();

                int idOrdine = rs.getInt("N_ordine");

                Spedizione s = new Spedizione(pacco, corriere, dataRitiro, partenza, destinazione, idOrdine);

                lista.add(s);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return lista;

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
    public listCoppieId selectIdOrdineSpedizione(int idCliente) {
        listCoppieId listaCoppie = new listCoppieId();
        String sql = "SELECT s.N_ordine,s.ID  FROM spedizione s JOIN ordine o ON o.N_ordine=s.N_ordine WHERE o.N_Cliente=?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idOrdine = rs.getInt("N_ordine");
                int idSpedizione = rs.getInt("ID");

                ArrayList<Integer> coppia = new ArrayList<>();
                coppia.add(idOrdine);
                coppia.add(idSpedizione);
                listaCoppie.add(coppia);
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaCoppie;
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
                N_ordine, N_corriere
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
         """;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, s.getPartenza().getNome());
            ps.setString(2, s.getPartenza().getCognome());

            ps.setString(3, s.getDestinazione().getNome());
            ps.setString(4, s.getDestinazione().getCognome());

            ps.setDate(5, java.sql.Date.valueOf(s.getDataRitiro()));

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


            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}


