package com.example.send_go.Dao.Mesaggio;

import com.example.send_go.Entity.Messaggio.Messaggio;

public interface SendGoDaoMessaggio {
    public int insertMessaggio(Messaggio msg, int ticketId);
    public void closeConnection();
    public boolean isConnected();
}
