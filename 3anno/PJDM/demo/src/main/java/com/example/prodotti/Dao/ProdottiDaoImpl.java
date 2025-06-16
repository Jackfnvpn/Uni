package com.example.prodotti.Dao;

import com.example.prodotti.Entity.ListaProdotti;
import com.example.prodotti.Entity.Prodotto;

import java.sql.*;

public class ProdottiDaoImpl implements ProdottiDao {
    private Connection conn;

    public ProdottiDaoImpl(String ip, String port, String dbName, String userName, String pwd) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbName + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            System.out.println("Tentativo connessione a: " + url + " con utente: " + userName);
            this.conn = DriverManager.getConnection(url, userName, pwd);
            System.out.println("Connessione al DB riuscita.");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC non trovato.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Errore nella connessione al DB:");
            e.printStackTrace();
        }
    }

    @Override
    public Prodotto loadProdotto(String name) {

        String query = "SELECT id, nome, quantita, prezzo FROM prodotto WHERE nome = \"" + name + "\"";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(query);

            Prodotto res = null;

            while (rset.next()) {
                String nome = rset.getString(2);
                int id = rset.getInt(1);
                double prezzo = rset.getDouble(4);
                int quantita = rset.getInt(3);
                res = new Prodotto(id, nome, quantita,prezzo);
                break;
            }

            rset.close();
            stmt.close();

            return res;

        } catch (SQLException e) {

            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int insertProdotto(Prodotto prodotto) {
        String SQL = "INSERT INTO prodotto(id,nome,quantita,prezzo) " + "VALUES(?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);

            pstmt.setInt(1, prodotto.getId());
            pstmt.setString(2, prodotto.getNome());
            pstmt.setInt(3, prodotto.getQuantita());
            pstmt.setDouble(4, prodotto.getPrezzo());

            System.out.println(" "+prodotto.getId()+" "+prodotto.getNome()+" "+prodotto.getQuantita()+" "+prodotto.getPrezzo());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ListaProdotti loadTuttiProdotti() {
        String query = "SELECT id, nome,quantita,prezzo FROM prodotto ";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(query);

            ListaProdotti res = new ListaProdotti();

            while (rset.next()) {
                String nome = rset.getString(2);
                int id = rset.getInt(1);
                double prezzo = rset.getDouble(4);
                int quantita = rset.getInt(3);

                Prodotto m = new Prodotto(id,nome,quantita,prezzo);
                res.add(m);
            }

            rset.close();
            stmt.close();

            return res;

        } catch (SQLException e) {

            e.printStackTrace();

            return null;
        }

    }

    @Override
    public boolean deleteProdotto(int id) {

        String SQL = "DELETE FROM prodotto WHERE id='" + id + "'";

        try {
            Statement statement = conn.createStatement();
            statement.execute(SQL);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}
