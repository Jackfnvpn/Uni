package com.example.prodotti.Dao;
import com.example.prodotti.Entity.ListaProdotti;
import com.example.prodotti.Entity.Prodotto;
public interface ProdottiDao {

    public Prodotto loadProdotto(String name);

    //public Prodotto loadProdottoByID(int inputId);

    public ListaProdotti loadTuttiProdotti();

    //public int getNumeroProdotti();

    public int insertProdotto(Prodotto prodotto);

    public boolean deleteProdotto(int idProdotto);

    //public int editProdotto(int inputId);

    public void closeConnection();
}
