package com.example.send_go.Dao.Tariffa;

public interface SendGoDaoTariffaInt {
    public void closeConnection();
    public boolean isConnected();
    public int insertTariffa(int id,double peso, double tariffa);
    public int updateTariffa(int idCorriere,double peso, double tariffa);
}
