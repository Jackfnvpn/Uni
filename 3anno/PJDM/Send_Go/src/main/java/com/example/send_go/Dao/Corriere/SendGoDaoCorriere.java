package com.example.send_go.Dao.Corriere;

import com.example.send_go.Entity.Corriere.Corriere;
import com.example.send_go.Entity.Corriere.ListCorriere;

public interface SendGoDaoCorriere {
    public ListCorriere getCorrieres();
    public ListCorriere getCorrieres(double pesoTassabile);
    public void closeConnection();
    public boolean isConnected();
    public int insertCorriere(String nome);
}
