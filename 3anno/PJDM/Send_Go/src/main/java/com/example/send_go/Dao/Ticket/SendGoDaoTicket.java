package com.example.send_go.Dao.Ticket;

import com.example.send_go.Entity.Ticket.ListTicket;
import com.example.send_go.Entity.Ticket.Ticket;
import com.example.send_go.Entity.Messaggio.Messaggio;


public interface SendGoDaoTicket {
    public ListTicket getListTicket(int idCliente);
    public Ticket getTicket(int idTicket);
    public int insertTicket(Ticket ticket);
    public ListTicket getListAllTicket();
    public void closeConnection();
    public boolean isConnected();
}