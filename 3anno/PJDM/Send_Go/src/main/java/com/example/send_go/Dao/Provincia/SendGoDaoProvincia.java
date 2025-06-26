package com.example.send_go.Dao.Provincia;

public interface SendGoDaoProvincia {
    public void closeConnection();
    public boolean isConnected();
    public boolean provExist(String nome);
}
