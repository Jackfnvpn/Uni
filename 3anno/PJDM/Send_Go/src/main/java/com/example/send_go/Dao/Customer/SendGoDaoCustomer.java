package com.example.send_go.Dao.Customer;

import com.example.send_go.Entity.Customer;

public interface SendGoDaoCustomer {
    public Customer checkCustomer(String email, String password);
    public void closeConnection();
    public boolean isConnected();
    public int insertCustomer(Customer customer);
}
