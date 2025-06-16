package com.example.send_go.Dao.Ordini;

import com.example.send_go.Entity.Ordine.ListOrder;
import com.example.send_go.Entity.Ordine.Ordine;

public interface SendGoDaoOrder {
    public ListOrder getListOrder(int idCliente);
    public int insertOrdine(Ordine ordine,int idCliente);
}
