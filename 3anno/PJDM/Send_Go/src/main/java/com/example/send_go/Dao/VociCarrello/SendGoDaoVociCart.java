package com.example.send_go.Dao.VociCarrello;

import com.example.send_go.Entity.Carrello.ListCarello;
import com.example.send_go.Entity.Carrello.VociCarrello;

import java.sql.SQLException;

public interface SendGoDaoVociCart {
    public int insertVociCart(VociCarrello vociCarrello,int idCliente);
    public ListCarello getListCarrello(int idCliente);
    public int clearCart(int idCliente);
    public void closeConnection();
    public boolean isConnected();
}
