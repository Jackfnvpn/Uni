package com.example.send_go.Dao.Spedizioni;

import com.example.send_go.Entity.Spedizione.ListSpedizione;
import com.example.send_go.Entity.Spedizione.Spedizione;
import com.example.send_go.Entity.Spedizione.listCoppieId;

import java.sql.SQLException;

public interface SendGoDaoSped {
    public int insertSpedizioni(Spedizione spedizione);
    public listCoppieId selectIdOrdineSpedizione(int idCliente);
    public ListSpedizione getListSpedizioni(int idCliente);
}
