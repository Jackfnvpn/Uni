package com.example.send_go.Dao.Ordini;

import com.example.send_go.Entity.Ordine.ListOrder;
import com.example.send_go.Entity.Ordine.Ordine;
import com.example.send_go.Entity.Spedizione.Spedizione;

import java.util.ArrayList;

public interface SendGoDaoOrder {
    public ListOrder getListOrder(int idCliente);
    public int insertOrdine(Ordine ordine,int idCliente);
    public void closeConnection();
    public boolean isConnected();
    public int clearCart(int idCliente);
    public int insertSpedizioni(Spedizione s);
    public boolean creaOrdineCompleto(Ordine ordine, ArrayList<Spedizione> spedizioni, int idCliente);
    }
